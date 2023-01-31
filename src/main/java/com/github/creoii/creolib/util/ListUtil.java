package com.github.creoii.creolib.util;

import java.util.List;

public class ListUtil {
    public static  <T> T cycle(List<T> list, int index) {
        if (index >= list.size() - 1) {
            return list.get(0);
        } else return list.get(index + 1);
    }
}
