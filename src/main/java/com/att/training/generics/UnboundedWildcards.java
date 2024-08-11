package com.att.training.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UnboundedWildcards {

    // An reference to a generic type where at least one type argument is a wildcard (as opposed to a concrete type).
    // A wildcard parameterized type denotes a family of types comprising concrete instantiations of a generic type.
    // In a way, a wildcard parameterized type is like an interface type:  you can declare reference variables of the type,
    // but you cannot create objects of the type.  A reference variable of an interface type or a wildcard parameterized type
    // can refer to an object of a compatible type.  For an interface, the compatible types are the class (or enum) types
    // that implement the interface.  For a wildcard parameterized type, the compatible types are the concrete instantiations
    // of the corresponding generic type that belong to the family of instantiations that the wildcard denotes.
    private void unboundedWildcards() {
        Collection<?> collection1 = new ArrayList<String>();
        Collection<?> collection2 = new LinkedList<Integer>();
        Map<String,?> pair1 = new HashMap<String, Long>();
        Map<String,?> pair2 = new HashMap<String, String>();

        List<String> stringList = new ArrayList<>();
        List<?> anyList = new ArrayList<Long>();
        anyList = stringList;
        //stringList = anyList;      // error
    }

    static class Collections {

        // Before Java 1.5, we only had raw types (like parameter collection)
        static String toStringRaw(Collection collection) {
            StringBuilder builder = new StringBuilder();
            for (Object o : collection) {
                builder.append(o);
            }
            return builder.toString();
        }

        @Test
        void givenListOfStrings_toStringRaw_concatenatesElements() {
            Collection<String> strings = List.of("Hello", "World", "Generics", "!");
            String result = toStringRaw(strings);
            assertThat(result).isEqualTo("HelloWorldGenerics!");
        }

        @Test
        void givenListOfLongs_toStringRaw_concatenatesElements() {
            Collection<Long> strings = List.of(12L, 3L, 456L);
            String result = toStringRaw(strings);
            assertThat(result).isEqualTo("123456");
        }

        // Let's add generics - 1st attempt
        static String toString1(Collection<Object> collection) {
            StringBuilder builder = new StringBuilder();
            for (Object o : collection) {
                builder.append(o);
            }
            return builder.toString();
        }

        @Test
        void callToString1WithListOfObjects() {
            Collection<Object> objects = List.of(new Object(), new Object());
            String result = toString1(objects);
        }

        @Test
        void callToString1WithListOfStrings() {
            Collection<String> strings = List.of("Hello", "World", "Generics", "!");
            // No good - this doesn't compile :-(
            //toString1(strings);
        }

        // Let's add generics - 2nd attempt
        // This is perfect for cases in which we don't care about the element type
        static String toString(Collection<?> collection) {
            StringBuilder builder = new StringBuilder();
            for (Object o : collection) {
                builder.append(o);
            }
            return builder.toString();
        }

        @Test
        void givenListOfStrings_toString_concatenatesElements() {
            Collection<String> strings = List.of("Hello", "World", "Generics", "!");
            String result = toString(strings);
            assertThat(result).isEqualTo("HelloWorldGenerics!");
        }

        @Test
        void givenListOfLongs_toString_concatenatesElements() {
            Collection<Long> longs = List.of(12L, 3L, 456L);
            String result = toString(longs);
            assertThat(result).isEqualTo("123456");
        }

        @SuppressWarnings("unchecked")
        // So if I'm getting the same result, why shouldn't I use raw classes?
        static String misbehavingToStringRaw(Collection collection) {
            StringBuilder builder = new StringBuilder();
            for (Object o : collection) {
                builder.append(o);
            }
            // We'd like the compiler to prohibit this!
            collection.add("Oops!");
            return builder.toString();
        }

        @Test
        void misbehavingToStringRaw_shouldModifyCollection() {
            List<Long> longs = new ArrayList<>(List.of(12L, 3L, 456L));
            misbehavingToStringRaw(longs);
            assertThatExceptionOfType(ClassCastException.class)
                    .isThrownBy(() -> { Long value = longs.get(3); });
        }

        static String toStringFixed(Collection<?> collection) {
            StringBuilder builder = new StringBuilder();
            for (Object o : collection) {
                builder.append(o);
            }
            // collection.add("Oops!"); // Doesn't compile! I can still add null, but at least that won't generate a runtime exception
            return builder.toString();
        }
    }
}