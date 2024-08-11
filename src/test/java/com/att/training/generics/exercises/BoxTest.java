package com.att.training.generics.exercises;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    class Box {

        private Object object;

        Object getObject() {
            return object;
        }

        void setObject(Object object) {
            this.object = object;
        }
    }

    /**
     * Edit test after generifying class {@link Box}.
     */
    @Test
    void genericBoxOfInteger_shouldReturnInteger() {
        var integerBox = new Box();
        int number = 67;
        integerBox.setObject(number);
        Integer object = (Integer) integerBox.getObject();
        assertThat(object).isEqualTo(number);
    }

    /**
     * Edit test after generifying class {@link Box}.
     */
    @Test
    void genericBoxOfString_shouldReturnString() {
        var stringBox = new Box();
        String name = "John";
        stringBox.setObject(name);
        String object = (String) stringBox.getObject();
        assertThat(object).isEqualTo(name);
    }
}