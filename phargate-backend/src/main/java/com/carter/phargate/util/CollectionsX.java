package com.carter.phargate.util;

import com.google.common.collect.Streams;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@UtilityClass
public class CollectionsX {

    public static <T> List<T> safeSubList(List<T> list, int fromIndex, int toIndex) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        if (fromIndex < 0 || toIndex < 0) {
            return Collections.emptyList();
        }

        if (fromIndex < list.size() && toIndex <= list.size()) {
            return list.subList(fromIndex, toIndex);
        }

        return list.subList(Math.min(fromIndex, list.size() - 1), Math.min(list.size(), toIndex));
    }

    public static <T> List<T> paddedSubList(List<T> list, int padding, Supplier<T> paddingSupplier, int fromIndex, int toIndex) {
        List<T> sub = safeSubList(list, fromIndex, toIndex);

        return paddedList(sub, padding, paddingSupplier);
    }

    public static <T> List<T> paddedList(List<T> list, int padding, Supplier<T> paddingSupplier) {
        int required = padding - (list.size() % padding);

        if (required == padding) {
            return list;
        }

        Stream<T> paddingStream = Stream.generate(paddingSupplier).limit(required);

        return Streams.concat(list.stream(), paddingStream).toList();
    }

}
