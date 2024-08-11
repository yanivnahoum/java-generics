package com.att.training.generics.exercises;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Mammal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.att.training.generics.exercises.Mammals.addMammals;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class Mammals {

    // Relax the restriction on the generic type of list mammals so that test givenListOfAnimals_shouldAdd10Mammals()
    // will be able to pass in a List<Animal> and the test will pass
    static void addMammals(List<Mammal> mammals, int count) {
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

    // After fixing method Mammals.add10Mammals(), uncomment the commented lines here and make sure the test passes
    @Test
    void givenListOfAnimals_shouldAdd10Mammals() {
        List<Animal> mammals = new ArrayList<>(asList(new Mammal(), new Mammal()));
//        addMammals(mammals, 10);
//        assertThat(mammals).hasSize(12);
    }
}
