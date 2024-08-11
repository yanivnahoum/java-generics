package com.att.training.generics.tricks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TypesafeHeterogeneousContainers {

    @Test
    void testFavorites() {
        Favorites favorites = new Favorites();
        String java = "Java";
        favorites.put(java);
        int number = 123;
        favorites.put(number);

        String s = favorites.get(String.class);
        assertThat(s).isSameAs(java);

        Integer i = favorites.get(Integer.class);
        assertThat(i).isSameAs(number);
    }

    @Test
    void testWithNonReifiableTypes() {
        // This container works well for reifiable types only.
        // Generic types will overwrite each other, and you can't get back the correct type :-(
        Favorites favorites = new Favorites();
        List<Integer> integers = new ArrayList<>();
        favorites.put(integers);
        List<String> strings = new ArrayList<>();
        favorites.put(strings);
        List<?> expected = favorites.get(ArrayList.class);
        assertThat(expected).isSameAs(strings);
    }
}

class Favorites {
    private final Map<Class<?>, Object> favorites = new HashMap<>();

    <T> void put(T thing) {
        favorites.put(thing.getClass(), thing);
    }

    <T> T get(Class<T> klass) {
        return klass.cast(favorites.get(klass));
    }
}
