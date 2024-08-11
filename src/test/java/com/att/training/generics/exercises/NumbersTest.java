package com.att.training.generics.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.att.training.generics.exercises.Numbers.sum;
import static org.assertj.core.api.Assertions.assertThat;

class Numbers {

    // Relax the restriction on the generic type of list numbers so that test givenListOfIntegers_shouldReturnSum()
    // will be able to pass in a List<Integer> and the test will pass
    static double sum(List<Double> numbers) {
        double sum = 0.0;
        for (Double value : numbers)
            sum += value;
        return sum;
    }
}

class NumbersTest {

    @Test
    void givenListOfDoubles_shouldReturnSum() {
        List<Double> numbers = List.of(1.2, 2.3, 3.4);
        double result = sum(numbers);
        assertThat(result).isEqualTo(6.9);
    }

    // After fixing method Numbers.sum(), uncomment the commented lines here and make sure the test passes
    @Test
    void givenListOfIntegers_shouldReturnSum() {
        List<Integer> numbers = List.of(1, 2, 3);
//        double result = sum(numbers);
//        assertThat(result).isEqualTo(6.0);
    }
}
