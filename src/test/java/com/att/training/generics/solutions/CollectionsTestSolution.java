package com.att.training.generics.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.att.training.generics.solutions.Collections.containsNull;
import static org.assertj.core.api.Assertions.assertThat;

class Collections {

    static boolean containsNull(Collection<?> c) {
        for (Object o : c) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }
}

class CollectionsTest {

    @Test
    void givenListOfStrings_withNull_shouldReturnTrue() {
        List<String> strings = Arrays.asList("Hello", "World", null, "!");
        boolean result = containsNull(strings);
        assertThat(result).isTrue();
    }

    @Test
    void givenListOfLongs_withoutNull_shouldReturnFalse() {
        List<Long> strings = Arrays.asList(12L, 3L, 456L);
        boolean result = containsNull(strings);
        assertThat(result).isFalse();
    }
}