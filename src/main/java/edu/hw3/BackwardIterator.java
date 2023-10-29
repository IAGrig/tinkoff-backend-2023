package edu.hw3;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator implements Iterator<Integer> {
    private final List<Integer> collection;
    private int cursor;

    public BackwardIterator(List<Integer> collection) {
        this.collection = collection;
        this.cursor = collection.size() ;
    }

    @Override
    public boolean hasNext() {
        return cursor-1 >= 0;
    }

    @Override
    public Integer next() {
        cursor--;
        return collection.get(cursor);
    }
}
