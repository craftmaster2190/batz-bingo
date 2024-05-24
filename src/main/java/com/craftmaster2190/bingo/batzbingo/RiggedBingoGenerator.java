package com.craftmaster2190.bingo.batzbingo;

import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.craftmaster2190.bingo.batzbingo.BingoCard.MIDDLE;

@Slf4j
@Component
public class RiggedBingoGenerator {

  public static final int WINNER = 52;

  public HashSet<BingoCard> generate(int countToGenerate) {
    var riggedNumbers = new int[]{ MIDDLE, 1, 3, 9, 12, 15, 17, 19, 25, 35, 37, 41, 46, 49 ,51, 52, 60, 61, 72, 74 };
    var riggedNumbersSet = Arrays.stream(riggedNumbers).boxed().collect(Collectors.toSet());
    var riggedBingoCards = new HashSet<BingoCard>(countToGenerate);
    var diagonalBingos = 0;
    while (riggedBingoCards.size() < countToGenerate) {
      var bingoCard = BingoCard.generate();

      if (Arrays.stream(bingoCard.getColumn(BingoLetter.G)).filter(number -> number == WINNER).count() == 0) {
        continue;
      }

      var anyPossibleBingos = bingoCard.possibleBingos().stream()
          .filter(possibleBingo -> {
            var possibleBingoSet = Arrays.stream(possibleBingo).boxed().collect(Collectors.toSet());
            return riggedNumbersSet.containsAll(possibleBingoSet);
          }
      ).toList();

      var validPossibleBingos = anyPossibleBingos.stream()
          .filter(possibleBingo -> {
            var possibleBingoSet = Arrays.stream(possibleBingo).boxed().collect(Collectors.toSet());
            return riggedNumbersSet.containsAll(possibleBingoSet) && possibleBingoSet.contains(WINNER);
          }
      ).toList();

      if (validPossibleBingos.isEmpty()) {
        continue;
      }

      if (validPossibleBingos.stream().anyMatch(possibleBingo -> Arrays.stream(possibleBingo).boxed().anyMatch(i -> i == MIDDLE))) {
        diagonalBingos++;
        if (diagonalBingos > countToGenerate / 3) {
          continue;
        }
      }

      log.info("For card\n{}\n\nanyPossibleBingos=\n{}\nvalidPossibleBingos=\n{}", bingoCard,
          IntArrayUtils.toString(anyPossibleBingos),
          IntArrayUtils.toString(validPossibleBingos));

      riggedBingoCards.add(bingoCard);
    }

    return riggedBingoCards;
  }
}
