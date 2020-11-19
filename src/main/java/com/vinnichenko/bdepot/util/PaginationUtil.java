package com.vinnichenko.bdepot.util;

import java.util.List;

/**
 * The type Pagination util.
 */
public final class PaginationUtil {

    private PaginationUtil() {
    }

    /**
     * Split by number items per page.
     *
     * @param <T>         the type parameter
     * @param list        the list
     * @param numberItems the number items
     * @param pageNumber  the page number
     * @return the list
     */
    public static <T> List<T> splitByNumberItemsPerPage(List<T> list, int numberItems, int pageNumber) {
        int start = (pageNumber - 1) * numberItems;
        int end = Math.min(pageNumber * numberItems, list.size());
        return list.subList(start, end);
    }

    /**
     * Amount pages int.
     *
     * @param size        the size
     * @param numberItems the number items
     * @return the int
     */
    public static int amountPages(int size, int numberItems) {
        return (int) Math.ceil(size / (double) numberItems);
    }
}
