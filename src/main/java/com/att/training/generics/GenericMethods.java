package com.att.training.generics;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenericMethods {

    static class Collections {

        static <T> String toString(Collection<T> collection) {
            StringBuilder builder = new StringBuilder();
            for (T o : collection) {
                builder.append(o);
            }
            return builder.toString();
        }

        @Test
        void givenListOfStrings_toString_concatenatesElements() {
            Collection<String> strings = List.of("Hello", "World", "Generics", "!");
            String result = Collections.toString(strings);
            assertThat(result).isEqualTo("HelloWorldGenerics!");
        }

        @Test
        void givenListOfLongs_toString_concatenatesElements() {
            Collection<Long> longs = List.of(12L, 3L, 456L);
            String result = Collections.toString(longs);
            assertThat(result).isEqualTo("123456");
        }

        @Test
        void compilerInference() {
            Collection<Integer> integers = List.of(1, 2, 3, 4);
            Collection<String> strings = List.of("Hello", "World", "Generics", "!");

            Collections.<Integer>toString(integers);
            Collections.<String>toString(strings);

            // The compiler happily infers the type parameter from the argument specified:
            Collections.toString(integers);
            Collections.toString(strings);
        }

        // Now let's try something more complex, like finding the maximal element:
        static <T> T tryGenericMax(Collection<T> collection) {
            Iterator<T> iterator = collection.iterator();
            T max = iterator.next();

            while (iterator.hasNext()) {
                T next = iterator.next();
                // compare next and max....
            }

            return max;
        }

        // 2 things to note:
        // 1. The return type and the collection type are related. This is not possible with wildcards
        // 2. The generic parameter and its bounds are defined before the return type
        // 3. More than one bound can be specified, only one of which may be a class (and it has to be first): class D <T extends A & B & C>
        static <T extends Comparable<T>> T max(Collection<T> collection) {
            Iterator<T> iterator = collection.iterator();
            T max = iterator.next();

            while (iterator.hasNext()) {
                T next = iterator.next();
                if (next.compareTo(max) > 0) {
                    max = next;
                }
            }

            return max;
        }

        @Test
        void maxShouldReturnMaximalInteger() {
            Collection<Integer> integers = List.of(1, 4, 3, 2);
            Integer maxInt = Collections.max(integers);
            assertThat(maxInt).isEqualTo(4);
        }

        @Test
        void maxShouldReturnMaximalString() {
            Collection<String> strings = List.of("Alice", "Bob", "Carl");
            String maxString = Collections.max(strings);
            assertThat(maxString).isEqualTo("Carl");
        }
    }
}
