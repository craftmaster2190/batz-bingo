package com.craftmaster2190.bingo.batzbingo;

import java.util.*;
import java.util.stream.Collectors;

public class IntArrayUtils {
  public static String toString(Collection<int[]> collection) {
    return collection.stream().map(Arrays::toString).collect(Collectors.joining("\n"));
  }
}
