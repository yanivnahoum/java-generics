package com.att.training.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WildcardsVsTypeParameters {

    static class Equal {
        // We can declare the method as a non-generic method using wildcard parameterized types as argument and return types.
        private void reverse(List<?> list) {
            // ...
        }

        // We can declare the method as a generic method with type parameters, that is, without using wildcards.
        // Which one is better?
        private <T> void reverse2(List<T> list) {
            // ...
        }
    }

    /*
     * The generic version is saying:  the  reverse method accepts a list with a certain, unknown element type and returns a list of that same type.
     * The wildcard version is saying:  the  reverse method accepts a list with a certain, unknown element type and returns a list of a potentially different type.
     * Remember, each occurrence of a wildcard stands for a potentially different type.  In principle, the reverse method could take a  List<Apple> and return a  List<Orange>.
     * There is nothing in the signature or the implementation of the  reverse method that indicates that "what goes in does come out".
     * In other words, the wildcard signature does not reflect our intent correctly.
     */
    static class Different {

        private List<?> reverse(List<?> list) {
            return null;
        }

        private <T> List<T> reverse2(List<T> list) {
            return null;
        }
    }

    static <T> void addToList(List<? super T> list, T obj) {
        // T is the most derived type possible here, so it can definitely be added to the list
        list.add(obj);
    }


    @Test
    void testAddToList() {
        List<Number> list = new ArrayList<>();
        addToList(list, 15.75);
        addToList(list, 2L);

        assertThat(list).containsExactly(15.75, 2L);
    }

    // Can we write method addToList without wildcards?
    // We can't do this, since lower bounds are not allowed on type parameters:
    // static <T, U super T> void addToList(List<U> list, T obj) {

    // We need to make sure that T can be assigned to U....

    static <T, U extends T> void addToList2(List<T> list, U obj) {
        // T is the most derived type possible here, so it can definitely be added to the list
        list.add(obj);
    }

    @Test
    void testAddToList2() {
        List<Number> list = new ArrayList<>();
        addToList2(list, 15.75);
        addToList2(list, 2L);

        assertThat(list).containsExactly(15.75, 2L);
    }
}
