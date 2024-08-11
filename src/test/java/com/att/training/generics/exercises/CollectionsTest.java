package com.att.training.generics.exercises;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.att.training.generics.exercises.Collections.containsNull;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class Collections {

    // Generify this method so that the tests below will still pass
    // and the compiler will prohibit adding items to the collection specified.
    static boolean containsNull(Collection c) {
        for (Object o : c) {
            if (o == null) {
                return true;
            }
        }
        // After generifying this method, this should not compile:
        c.add("hello");
        return false;
    }
}

class CollectionsTest {

    @Test
    void givenListOfStrings_withNull_shouldReturnTrue() {
        var strings = new ArrayList<>(asList("Hello", "World", null, "!"));
        boolean result = containsNull(strings);
        assertThat(result).isTrue();
    }

    @Test
    void givenListOfLongs_withoutNull_shouldReturnFalse() {
        var longs = new ArrayList<>(asList(12L, 3L, 456L));
        boolean result = containsNull(longs);
        assertThat(result).isFalse();
    }
}