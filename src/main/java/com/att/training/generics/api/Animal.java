package com.att.training.generics.api;

public class Animal {

    private final int generation;

    public Animal() {
        this(0);
    }

    public Animal(int generation) {
        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }
}
