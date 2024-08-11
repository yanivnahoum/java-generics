package com.att.training.generics.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.assertj.core.api.Assertions.assertThat;

class WildcardToParameterizedTest {

    static <U, T extends U> void copy(List<T> source, List<U> destination) {
        validateSizes(source, destination);

        ListIterator<U> di = destination.listIterator();
        for (T t : source) {
            di.next();
            di.set(t);
        }
    }

    private static void validateSizes(List<?> source, List<?> destination) {
        if (source.size() > destination.size()) {
            throw new IndexOutOfBoundsException("Source does not fit in destination");
        }
    }

    @Test
    void whenCopyingIntegersIntoNumbers_listsShouldBeIdentical() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Number> numbers = Arrays.asList(5L, 6L, 7L);

        copy(integers, numbers);

        assertThat(numbers).containsExactlyElementsOf(integers);
    }

    @Test
    void whenCopyingStringsIntoObjects_listsShouldBeIdentical() {
        List<String> numbers = Arrays.asList("one", "two", "three");
        List<Object> objects = Arrays.asList(new Object(), new Object(), new Object());

        copy(numbers, objects);

        assertThat(objects).containsExactlyElementsOf(numbers);
    }
}
