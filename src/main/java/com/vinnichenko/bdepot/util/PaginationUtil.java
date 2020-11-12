package com.vinnichenko.bdepot.util;

import java.util.List;

public class PaginationUtil {
    public static <T> List<T> splitByNumberItemsPerPage(List<T> list, int numberItems, int pageNumber) {
        int start = (pageNumber - 1) * numberItems;
        int end = Math.min(pageNumber * numberItems, list.size());
        return list.subList(start, end);
    }

    public static int amountPages(int size, int numberItems) {
        return (int) Math.ceil(size / (double) numberItems);
    }
}
