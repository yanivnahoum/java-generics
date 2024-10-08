package com.att.training.generics.exercises;

import com.att.training.generics.api.Animal;
import com.att.training.generics.api.Dog;
import com.att.training.generics.api.Mammal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains methods that are overly restrictive. In the tests below, the commented lines don't compile.
 * Uncomment them and make the required changes to this class so that the tests compile and pass.
 */
class GenericList<E> {

    private final List<E> elements = new LinkedList<>();

    void add(E element) {
        elements.add(element);
    }

    void add(Supplier<E> supplier) {
        elements.add(supplier.get());
    }

    void addAll(Collection<E> elements) {
        this.elements.addAll(elements);
    }

    void remove(Object o) {
        this.elements.remove(o);
    }

    void removeAll(Collection<E> elements) {
        this.elements.removeAll(elements);
    }

    void removeIf(Predicate<E> filter) {
        Iterator<E> iterator = elements.iterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    void map(Function<E, E> mapper) {
        ListIterator<E> iterator = elements.listIterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            iterator.set(mapper.apply(next));
        }
    }

    int size() {
        return elements.size();
    }

    List<E> getElements() {
        return elements;
    }
}

class GenericListTest {

    private GenericList<Mammal> mammals;

    @BeforeEach
    void setUp() {
        mammals = new GenericList<>();
    }

    @Test
    void whenMammal_isAddedToListOfMammals_sizeIsOne() {
        mammals.add(new Mammal());
        assertThat(mammals.size()).isOne();
    }

    @Test
    void whenDog_isAddedToListOfMammals_sizeIsOne() {
        mammals.add(new Dog());
        assertThat(mammals.size()).isOne();
    }

    @Test
    void whenCollectionOf2Mammals_isAddedToEmptyListOfMammals_sizeIs2() {
        List<Mammal> moreMammals = List.of(new Mammal(), new Mammal());
        mammals.addAll(moreMammals);
        assertThat(mammals.size()).isEqualTo(2);
    }

    /**
     * Allow method addAll of {@link GenericList} to accept a {@link Collection} of a type that derives from {@link Mammal}
     */
    @Test
    void whenCollectionOf2Dogs_isAddedToEmptyListOfMammals_sizeIs2() {
        List<Dog> dogs = List.of(new Dog(), new Dog());
//        mammals.addAll(dogs);
//        assertThat(mammals.size()).isEqualTo(2);
    }

    @Test
    void whenMammal_isAddedToListOfMammals_usingSupplier_sizeIsOne() {
        mammals.add(Mammal::new);
        assertThat(mammals.size()).isOne();
    }

    /**
     * Allow method add of {@link GenericList} to accept a {@link Supplier} of a type that derives from {@link Mammal}
     */
    @Test
    void whenDog_isAddedToListOfMammals_usingSupplier_sizeIsOne() {
        Supplier<Dog> dogSupplier = Dog::new;
//        mammals.add(dogSupplier);
//        assertThat(mammals.size()).isOne();
    }

    @Test
    void whenExistingMammal_isRemovedFromListOfMammals_listIsEmpty() {
        Mammal mammal = new Mammal();
        mammals.add(mammal);

        mammals.remove(mammal);

        assertThat(mammals.size()).isZero();
    }

    @Test
    void whenString_isRemovedFromListOfMammals_listRemainsUntouched() {
        Mammal mammal = new Mammal();
        mammals.add(mammal);

        mammals.remove("testing");

        assertThat(mammals.size()).isOne();
    }

    @Test
    void whenCollectionOf2Mammals_isRemovedFromListOf2Mammals_listIsEmpty() {
        List<Mammal> moreMammals = List.of(new Mammal(), new Mammal());
        mammals.addAll(moreMammals);

        mammals.removeAll(moreMammals);

        assertThat(mammals.size()).isZero();
    }

    /**
     * Allow method removeAll of {@link GenericList} to accept a {@link Collection} of an unknown type
     */
    @Test
    void whenCollectionOfStrings_isRemovedFromListOfMammals_listRemainsUntouched() {
        List<Mammal> moreMammals = List.of(new Mammal(), new Mammal());
        mammals.addAll(moreMammals);

        List<String> strings = List.of("testing", "1,2,3");
//        mammals.removeAll(strings);

//        assertThat(mammals.size()).isEqualTo(2);
    }

    @Test
    void whenListOfMammals_isFiltered_listContainsAtLeastSecondGenMammals() {
        List<Mammal> moreMammals = List.of(new Mammal(1), new Mammal(2), new Mammal(3));
        mammals.addAll(moreMammals);
        Predicate<Mammal> isFirstGenMammal = m -> m.getGeneration() == 1;

        mammals.removeIf(isFirstGenMammal);

        assertThat(mammals.getElements()).extracting(Mammal::getGeneration)
                                         .containsExactly(2, 3);
    }

    /**
     * Allow method removeIf of {@link GenericList} to accept a {@link Predicate} of a  super-type of {@link Mammal}
     */
    @Test
    void whenListOfMammals_isFiltered_listContainsNonNullMammals() {
        List<Mammal> moreMammals = asList(new Mammal(), null, new Mammal());
        mammals.addAll(moreMammals);
        Predicate<Object> isNull = Objects::isNull;

        //mammals.removeIf(isNull);

        //assertThat(mammals.getElements()).allMatch(Objects::nonNull);
    }

    @Test
    void whenListOfMammals_isMappedToNextGeneration_listContainsYoungerMammals() {
        List<Mammal> moreMammals = List.of(new Mammal(1), new Mammal(2), new Mammal(3));
        mammals.addAll(moreMammals);
        Function<Mammal, Mammal> createMammal = this::createMammal;

        mammals.map(createMammal);

        assertThat(mammals.getElements()).extracting(Mammal::getGeneration)
                                         .containsExactly(2, 3, 4);
    }

    private Mammal createMammal(Mammal parent) {
        return new Mammal(parent.getGeneration() + 1);
    }

    /**
     * Allow method map of {@link GenericList} to accept a {@link Function} from super-type of {@link Mammal}
     * to a subtype of {@link Mammal}
     */
    @Test
    void whenListOfMammals_isMappedToNextGenerationOfDogs_listContainsYoungerMammals() {
        List<Mammal> moreMammals = List.of(new Mammal(1), new Mammal(2), new Mammal(3));
        mammals.addAll(moreMammals);
        Function<Animal, Dog> createDog = this::createDog;

//        mammals.map(createDog);

//        assertThat(mammals.getElements())
//                .allMatch(e -> e instanceof Dog)
//                .extracting(Mammal::getGeneration)
//                .containsExactly(2, 3, 4);
    }

    private Dog createDog(Animal parent) {
        return new Dog(parent.getGeneration() + 1);
    }
}
