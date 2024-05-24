package com.craftmaster2190.bingo.batzbingo;

import java.util.*;

public class ListUtils {
  public static <T> List<T> drawRandom(List<T> list, int count) {
    List<T> copy = new ArrayList<>(list);
    Collections.shuffle(copy);
    return copy.subList(0, count);
  }
}
