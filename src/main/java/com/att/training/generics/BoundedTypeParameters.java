package com.att.training.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoundedTypeParameters {

    class Box<T> {

        private T object;

        T getObject() {
            return object;
        }

        void setObject(T object) {
            this.object = object;
        }
    }

    // We'd like to extend our simple box with one that holds numbers
    // and can therefore expose a method that tests for parity:
    class NumberBox<T extends Number> extends Box<T> {

        boolean isEven() {
            T object = getObject();
            return object != null && object.intValue() % 2 == 0;
        }
    }

    @Test
    void isEvenShouldReturnTrue_givenNumberBoxWithEvenNumber() {
        NumberBox<Integer> numberBox = new NumberBox<>();
        numberBox.setObject(40);

        boolean result = numberBox.isEven();

        assertThat(result).isTrue();
    }

    @Test
    void isEvenShouldReturnFalse_givenNumberBoxWithEvenNumber() {
        NumberBox<Integer> numberBox = new NumberBox<>();
        numberBox.setObject(41);

        boolean result = numberBox.isEven();

        assertThat(result).isFalse();
    }
}
