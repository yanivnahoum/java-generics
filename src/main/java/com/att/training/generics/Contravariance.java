package com.att.training.generics;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Dog;
import com.att.training.generics.api.Mammal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

class Contravariance {

    void lowerBound() {
        //Contravariance: if [Object -> Animal -> Mammal -> Dog] then [A<Object> <- A<Animal> <- A<Mammal> <- A<Dog>]
        // Of course generic types in java are NOT contravariant, they are invariant
        // List<Dog> dogs = new ArrayList<Mammal>();   // No way!

        // Wildcards to the rescue again! This is called use-site variance (as opposed to declaration-site variance
        List<? super Dog> dogs = new ArrayList<Dog>();
        dogs = new ArrayList<Mammal>();
        dogs = new ArrayList<Animal>();
        dogs = new ArrayList<Object>();
        // ? super X denotes the family of types that are super types of X (including X itself)
    }

    void contravarianceNeeded(Comparator<Dog> dogComparator, Comparator<Mammal> mammalComparator, Comparator<Animal> animalComparator) {
        List<Dog> dogs = asList(new Dog(), new Dog(), new Dog());
        Optional<Dog> maxDog = max(dogs, dogComparator);
//        maxDog = max(dogs, mammalComparator);
//        maxDog = max(dogs, animalComparator);

        // Mammal -> Dog, and we'd like Comparator<Dog> to accept a Comparator<Mammal> (the opposite direction)
        // This is contravariance! But Comparable<T> (like all generic classes and interfaces in java) is invariant.
        // How can we relax the restriction on the required comparable? Use-site contravariance to the rescue!
    }

    Optional<Dog> max(List<Dog> dogs, Comparator<Dog> comparator) {
        if (dogs.isEmpty()) {
            return Optional.empty();
        }

        Iterator<Dog> iterator = dogs.iterator();
        Dog max = iterator.next();
        while (iterator.hasNext()) {
            Dog next = iterator.next();
            if (comparator.compare(max, next) > 0) {
                max = next;
            }
        }

        return Optional.of(max);
    }
}