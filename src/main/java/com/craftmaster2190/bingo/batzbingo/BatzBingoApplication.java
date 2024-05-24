package com.craftmaster2190.bingo.batzbingo;

import java.util.Arrays;
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

  public static void main(String[] args) {
    SpringApplication.run(BatzBingoApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    BingoCard bingoCard = BingoCard.generate();
    log.info("\n{}\n\nPossible Bingos:\n{}", bingoCard, bingoCard.possibleBingosToString());

    var riggedBingoCards = riggedBingoGenerator.generate(10);

    for (var b : riggedBingoCards) {
      // TODO Generate PDFs
    }
  }


}

