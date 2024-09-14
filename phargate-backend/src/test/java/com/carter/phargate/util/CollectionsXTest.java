package com.carter.phargate.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionsXTest {

    @Test
    void paddedList() {

        List<Integer> a = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> b = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 1, 2, 3);

        AtomicInteger atomicInteger = new AtomicInteger(0);

        assertThat(CollectionsX.paddedList(a, 10, atomicInteger::incrementAndGet)).isEqualTo(expected);
        assertThat(CollectionsX.paddedList(b, 10, atomicInteger::incrementAndGet)).isEqualTo(b);
        assertThat(CollectionsX.paddedList(b, 5, atomicInteger::incrementAndGet)).isEqualTo(b);

    }

}