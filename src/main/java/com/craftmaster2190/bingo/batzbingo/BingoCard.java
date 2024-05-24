package com.craftmaster2190.bingo.batzbingo;

import com.google.common.base.Preconditions;
import java.util.*;
import java.util.stream.Collectors;

public class BingoCard {
  private final Map<BingoLetter, int[]> grid = new HashMap<>();

  public final static int MIDDLE = -1;

  {
    // Init middle
    getColumn(BingoLetter.N)[2] = MIDDLE;
  }

  public static BingoCard generate() {
    BingoCard bingoCard = new BingoCard();
    for (BingoLetter letter : BingoLetter.values()) {
      var randomNumbers = new ArrayList<>(ListUtils.drawRandom(letter.numbers(), letter.columnSize()));
      if (letter == BingoLetter.N) {
        randomNumbers.add(2, MIDDLE);
      }
      for (int i = 0; i < randomNumbers.size(); i++) {
        bingoCard.add(letter, i, randomNumbers.get(i));
      }
    }
    return bingoCard;
  }

  public BingoCard add(BingoLetter letter, int row, int number) {
    Preconditions.checkArgument(0 <= row && row <= 4, "Invalid row %s", row);
    Preconditions.checkArgument((letter == BingoLetter.N && number == MIDDLE && row == 2)
        || (letter.getMin() <= number && number <= letter.getMax()), "Invalid number %s for letter %s", number, letter);
    var column = getColumn(letter);
    column[row] = number;
    return this;
  }

  public int[] getColumn(BingoLetter letter) {
    return grid.computeIfAbsent(letter, k -> new int[5]);
  }

  public String toString() {
    var row = "%2s   %2s   %2s   %2s   %2s";
    var builder = new ArrayList<String>(6);
    builder.add(row.formatted(Arrays.stream(BingoLetter.values()).map(Enum::name).toArray()));
    for (int rowIndex = 0; rowIndex <= 4; rowIndex++) {
      final var rowIndex$ = rowIndex;
      builder.add(row.formatted(Arrays.stream(BingoLetter.values()).map(letter -> grid.get(letter)[rowIndex$]).toArray()));
    }
    return String.join("\n", builder);
  }

  public List<int[]> possibleBingos() {
    var bingos = new ArrayList<int[]>();
    // Rows
    for (int i = 0; i <= 4; i++) {
      bingos.add(new int[] {grid.get(BingoLetter.B)[i], grid.get(BingoLetter.I)[i], grid.get(BingoLetter.N)[i], grid.get(BingoLetter.G)[i], grid.get(BingoLetter.O)[i]});
    }

    // Columns
    for (var letter : BingoLetter.values()) {
      bingos.add(grid.get(letter));
    }

    // Diagonals
    bingos.add(new int[] {grid.get(BingoLetter.B)[0], grid.get(BingoLetter.I)[1], grid.get(BingoLetter.N)[2], grid.get(BingoLetter.G)[3], grid.get(BingoLetter.O)[4]});
    bingos.add(new int[] {grid.get(BingoLetter.B)[4], grid.get(BingoLetter.I)[3], grid.get(BingoLetter.N)[2], grid.get(BingoLetter.G)[1], grid.get(BingoLetter.O)[0]});
    return bingos;
  }

  public String possibleBingosToString() {
    return IntArrayUtils.toString(possibleBingos());
  }
}

