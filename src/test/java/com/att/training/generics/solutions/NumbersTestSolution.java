package com.att.training.generics.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.att.training.generics.solutions.Numbers.sum;
import static org.assertj.core.api.Assertions.assertThat;

class Numbers {

    static double sum(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number n : numbers)
            sum += n.doubleValue();
        return sum;
    }
}

class NumbersTest {

    @Test
    void givenListOfDoubles_shouldReturnSum() {
        List<Double> numbers = Arrays.asList(1.2, 2.3, 3.4);
        double result = sum(numbers);
        assertThat(result).isEqualTo(6.9);
    }
    
    @Test
    void givenListOfIntegers_shouldReturnSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        double result = sum(numbers);
        assertThat(result).isEqualTo(6.0);
    }
}
