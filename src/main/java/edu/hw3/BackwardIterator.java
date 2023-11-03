package edu.hw3;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator implements Iterator<Integer> {
    private final List<Integer> collection;
    private int index;

    public BackwardIterator(List<Integer> collection) {
        this.collection = collection;
        this.index = collection.size();
    }

    @Override
    public boolean hasNext() {
        return index - 1 >= 0;
    }

    @Override
    public Integer next() {
        index--;
        return collection.get(index);
    }
}
