package com.att.training.generics;

import java.util.List;
import java.util.ListIterator;

class CapturingWildcards {

    void foo(List<? extends String> names) {
        ListIterator<? extends String> listIterator = names.listIterator();
        while (listIterator.hasNext()) {
            // The following won't compile: ListIterator.set(capture<? extends java.lang.String>) cannot be applied to (capture<? extends java.lang.String>) 
            //listIterator.set(listIterator.next())
            fooHelper(listIterator);
        }
    }

    private <T> void fooHelper(ListIterator<T> listIterator) {
        listIterator.set(listIterator.next());
    }
}

