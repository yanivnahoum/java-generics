package com.att.training.generics.solutions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    // T (as in type) is probably the most used type parameter. Others are U, V. In maps - K & V
    class Box<T> {

        private T object;

        T getObject() {
            return object;
        }

        void setObject(T object) {
            this.object = object;
        }
    }

    @Test
    void genericBoxOfInteger_shouldReturnInteger() {
        var integerBox = new Box<Integer>();
        int number = 67;

        integerBox.setObject(number);

        Integer object = integerBox.getObject();
        assertThat(object).isEqualTo(number);
    }

    @Test
    void genericBoxOfString_shouldReturnString() {
        var stringBox = new Box<String>();
        String name = "John";

        stringBox.setObject(name);

        String object = stringBox.getObject();
        assertThat(object).isEqualTo(name);
    }
}