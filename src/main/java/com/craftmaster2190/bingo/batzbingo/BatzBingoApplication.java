package com.craftmaster2190.bingo.batzbingo;

import com.itextpdf.text.BaseColor;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class BatzBingoApplication implements ApplicationRunner {

  private final RiggedBingoGenerator riggedBingoGenerator;
  private final BingoPdfGenerator bingoPdfGenerator;

  public static void main(String[] args) {
    SpringApplication.run(BatzBingoApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
//    BingoCard bingoCard = BingoCard.generate();
//    log.info("\n{}\n\nPossible Bingos:\n{}", bingoCard, bingoCard.possibleBingosToString());

    var bingoCardPath = Path.of("bingo-cards");
    bingoCardPath.toFile().mkdirs();

    var numberToGenerate = 50;

    BaseColor blue = new BaseColor(0, 255, 255);
    BaseColor yellow = new BaseColor(251, 255, 81);
    BaseColor green = new BaseColor(144, 238, 144);

    for (var color : new BaseColor[]{ blue, yellow, green }) {
      var set = new HashSet<BingoCard>();
      while ( set.size() < numberToGenerate) {
        set.add(BingoCard.generate());
      }

      var list = new ArrayList<>(set);
      for (var i = 0; i < list.size(); i++) {
        var bingoCard = list.get(i);
        bingoPdfGenerator.printBingoCard(bingoCard, bingoCardPath.resolve("bingoCard-" + colorToString(color) + "-" + i + ".pdf").toString(), color);
      }
    }

    BaseColor red = new BaseColor(250, 160, 160);
    List<BingoCard> riggedBingoCards = new ArrayList<>(riggedBingoGenerator.generate(numberToGenerate));
    for (int i = 0; i < riggedBingoCards.size(); i++) {
      var riggedBingoCard = riggedBingoCards.get(i);
      bingoPdfGenerator.printBingoCard(riggedBingoCard, bingoCardPath.resolve("riggedBingoCard-" + i + ".pdf").toString(), red);
    }
  }

  public static String colorToString(BaseColor color) {
    return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }
}

