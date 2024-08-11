package com.att.training.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenericTypes {

    // We need a class that can hold a pair of any two types of objects.
    // We'd like to avoid writing a class per pair type: IntStringPair, StringIntPair, PersonStringPair, etc.
    static class ObjectPair {

        private final Object left;
        private final Object right;

        ObjectPair(Object left, Object right) {
            this.left = left;
            this.right = right;
        }

        Object getLeft() {
            return left;
        }

        Object getRight() {
            return right;
        }
    }

    @Test
    void pairOfIntegerAndString_shouldReturnObjectAsIntegerAndObjectAsString() {
        int integer = 10;
        String string = "ten";
        ObjectPair intStringPair = new ObjectPair(integer, string);

        // We need the cast here, the compiler can't guarantee type safety :-(
        Integer left = (Integer) intStringPair.getLeft();
        assertThat(left).isEqualTo(integer);

        // We need the cast here, the compiler can't guarantee type safety :-(
        String right = (String) intStringPair.getRight();
        assertThat(right).isEqualTo(string);
    }

    @Test
    void pairOfStringAndInteger_shouldReturnObjectAsStringAndObjectAsInteger() {
        String string = "ten";
        int integer = 10;
        ObjectPair stringIntPair = new ObjectPair(string, integer);

        // We need the cast here, the compiler can't guarantee type safety :-(
        String left = (String) stringIntPair.getLeft();
        assertThat(left).isEqualTo(string);

        // We need the cast here, the compiler can't guarantee type safety :-(
        Integer right = (Integer) stringIntPair.getRight();
        assertThat(right).isEqualTo(integer);
    }

    // Let's generify the class:
    static class Pair<L, R> {

        private final L left;
        private final R right;

        Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        L getLeft() {
            return left;
        }

        R getRight() {
            return right;
        }
    }

    @Test
    void pairOfIntegerAndString_shouldReturnIntegerAndString() {
        int integer = 10;
        String string = "ten";
        Pair<Integer, String> intStringPair = new Pair<>(integer, string);

        Integer left = intStringPair.getLeft();
        assertThat(left).isEqualTo(integer);

        String right = intStringPair.getRight();
        assertThat(right).isEqualTo(string);
    }

    @Test
    void pairOfStringAndInteger_shouldReturnStringAndInteger() {
        String string = "ten";
        int integer = 10;
        Pair<String, Integer> stringIntPair = new Pair<>(string, integer);

        String left = stringIntPair.getLeft();
        assertThat(left).isEqualTo(string);

        Integer right = stringIntPair.getRight();
        assertThat(right).isEqualTo(integer);
    }
}
