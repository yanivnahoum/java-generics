package com.att.training.generics;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Dog;
import com.att.training.generics.api.Mammal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class Covariance {

    @Test
    void arraysAreCovariant() {
        // Animal -> Mammal, and therefore:
        Animal animal = new Mammal();

        // And this feels intuitive:
        Animal[] animals = new Mammal[10];

        // And it allows us to write Arrays.hashCode() ONCE for all reference types:
        Arrays.hashCode(new String[]{"a", "b", "c"});
        Arrays.hashCode(new Integer[]{1, 2, 3});

        // But we can easily get into trouble here:
        animals[0] = new Mammal();
        animals[1] = new Dog();
        assertThatExceptionOfType(ArrayStoreException.class)
                .isThrownBy(() -> animals[2] = new Animal());
    }

    // Generics in java are invariant
    void listIsNotCovariant() {
        // Animal -> Mammal, and therefore:
        Animal animal = new Mammal();

        // But this doesn't work:
        List<Mammal> mammals = new ArrayList<>();
        // List<Animal> animals = mammals;

        // If it did, we could do this:
        // animals.add(new Animal());
        // Mammal mammal = mammals.get(0);   --> ClassCastException!!
    }

    void upperBound() {
        // ? extends X denotes the family of types that are derived from X (including X itself)
        // During compilation, the wildcard type parameter is erased and replaced with the upper bound (Number)
        List<? extends Number> numbers = new ArrayList<Long>();
        numbers = new ArrayList<Integer>();
        numbers = new ArrayList<Double>();
    }
}
