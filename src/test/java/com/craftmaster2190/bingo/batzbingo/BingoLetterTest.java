package com.craftmaster2190.bingo.batzbingo;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class BingoLetterTest {
  @Test
  void test_B() {
    BingoLetter letter = BingoLetter.B;
    assertThat(letter.getMin()).isEqualTo(1);
    assertThat(letter.getMax()).isEqualTo(15);
    assertThat(letter.columnSize()).isEqualTo(5);
    assertThat(letter.numbers()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
  }

  @Test
  void test_I() {
    BingoLetter letter = BingoLetter.I;
    assertThat(letter.getMin()).isEqualTo(16);
    assertThat(letter.getMax()).isEqualTo(30);
    assertThat(letter.columnSize()).isEqualTo(5);
    assertThat(letter.numbers()).containsExactly(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
  }

  @Test
  void test_N() {
    BingoLetter letter = BingoLetter.N;
    assertThat(letter.getMin()).isEqualTo(31);
    assertThat(letter.getMax()).isEqualTo(45);
    assertThat(letter.columnSize()).isEqualTo(4);
    assertThat(letter.numbers()).containsExactly(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45);
  }

  @Test
  void test_G() {
    BingoLetter letter = BingoLetter.G;
    assertThat(letter.getMin()).isEqualTo(46);
    assertThat(letter.getMax()).isEqualTo(60);
    assertThat(letter.columnSize()).isEqualTo(5);
    assertThat(letter.numbers()).containsExactly(46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60);
  }

  @Test
  void test_O() {
    BingoLetter letter = BingoLetter.O;
    assertThat(letter.getMin()).isEqualTo(61);
    assertThat(letter.getMax()).isEqualTo(75);
    assertThat(letter.columnSize()).isEqualTo(5);
    assertThat(letter.numbers()).containsExactly(61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75);
  }
}