package com.att.training.generics;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Dog;
import com.att.training.generics.api.Mammal;

class BoundedWildcards {

    static class Box<T> {

        private T t;

        Box(T t) {
            this.t = t;
        }

        void put(T t) {
            this.t = t;
        }

        T take() {
            return t;
        }

        boolean equalTo(Box<T> other) {
            return t.equals(other.t);
        }

        Box<T> copy() {
            return new Box<>(t);
        }

        @Override
        public String toString() {
            return "Box[" + t.toString() + "]";
        }
    }

    static void concreteGenericType(Box<Mammal> box) {
        box.put(new Dog());
        box.put(new Mammal());
        box.put(null);

        String s = box.toString();

        Mammal mammal = box.take();

        Box<Mammal> copy = box.copy();
        boolean equal = box.equalTo(copy);
        equal = box.equalTo(box);
    }

    // Upper bounds are "sort of" read-only
    static void upperBound(Box<? extends Animal> box) {
        // box.put(new Dog()); // error, there's no lower bound to this hierarchy....
        // box.put(new Mammal()); // error
        // box.put(new Animal()); // error
        box.put(null); // ok

        String s = box.toString();// ok

        // Dog dog = box.take(); // error, even if we know that the box is referencing a Box<Dog>
        // The returned object of "unknown" type is known to be compatible to the upper bound.
        // Hence we can assign the result of #take to a reference variable of type Animal in our example.
        Animal animal = box.take(); // ok

        Box<? extends Animal> copy = box.copy();
        // boolean equal = box.equalTo(copy); // error - the compiler doesn't know the concrete argument type, only the family of types to which it belongs
        // equal = box.equalTo(box); // still an error!
    }

    // Lower bounds are "sort of" write-only
    static void lowerBound(Box<? super Mammal> box) {
        // Methods that take an argument of the "unknown" type can be invoked with either null
        // or an argument whose type is the lower bound or a subtype thereof.
        box.put(new Mammal()); // ok
        box.put(new Dog()); // ok
        // box.put(new Animal()); // error, this could be a Box<Mammal>
        box.put(null); // ok

        String s = box.toString();// ok

        // Mammal l = box.take(); // error, could be a Box<Animal>
        // Animal n = box.take(); // error, could be a Box<Object>

        // Methods that return a value of the "unknown" type can be invoked, but only if no assumptions are made
        // regarding the type of the returned object and it is treated like an Object
        Object o = box.take(); // ok

        Box<? super Mammal> copy = box.copy();
//        boolean equal = box.equalTo(copy); // error - the compiler doesn't know the concrete argument type, only the family of types to which it belongs
//        equal = box.equalTo(box); // still an error!
    }

    static void unbounded(Box<? extends Object> box) {
        //box.put("xyz"); // error
        box.put(null); // ok

        //String s = box.take(); // error could be any box at alls
        Object o = box.take(); // ok

        //boolean equal = box.equalTo(box); // error
        //equal = box.equalTo(new Box<String>("abc")); // error

        Box<?> box1 = box.copy(); // ok
        //Box<String> box2 = box.copy(); // error
    }
}
