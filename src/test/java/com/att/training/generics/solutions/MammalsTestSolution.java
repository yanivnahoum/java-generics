package com.att.training.generics.solutions;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Mammal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.att.training.generics.solutions.Mammals.addMammals;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class Mammals {

    static void addMammals(List<? super Mammal> mammals, int count) {
        for (int i = 0; i < count; i++) {
            mammals.add(new Mammal());
        }
    }
}

class MammalsTest {

    @Test
    void givenListOfMammals_shouldAdd10Mammals() {
        List<Mammal> mammals = new ArrayList<>(asList(new Mammal(), new Mammal()));
        addMammals(mammals, 10);
        assertThat(mammals).hasSize(12);
    }

    @Test
    void givenListOfAnimals_shouldAdd10Mammals() {
        List<Animal> mammals = new ArrayList<>(asList(new Mammal(), new Mammal()));
        addMammals(mammals, 10);
        assertThat(mammals).hasSize(12);
    }
}
