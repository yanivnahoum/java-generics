package com.att.training.generics.tricks;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class SuperTypeTokens {

    @Test
    void typeReferenceOfString_shouldReportTypeCorrectly() {
        TypeToken<String> typeToken = new TypeToken<String>() {};
        assertThat(typeToken.getType()).isEqualTo(String.class);
    }

    private abstract static class StringList implements List<String> {}

    @Test
    void typeReferenceOfListOfStrings_shouldReportTypeCorrectly() {
        TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
        assertThat(typeToken.getType()).isEqualTo(StringList.class.getGenericInterfaces()[0]);
    }

    @Test
    <T> void testVariableTypeTokenNotAllowed() {
        try {
            TypeToken<T> typeToken = new TypeToken<T>() {};
            fail("Should have thrown IllegalArgumentException!");
        }
        catch (IllegalArgumentException expected) {
        }
    }
}

abstract class TypeToken<T> {

    private final Type type;

    TypeToken() {
        Type superClass = getClass().getGenericSuperclass();
        verify(superClass instanceof ParameterizedType);

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        verify(!(type instanceof TypeVariable));
    }

    private static void verify(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Internal error: TypeToken cannot be constructed without actual type information!");
        }
    }

    public Type getType() {
        return type;
    }
}
