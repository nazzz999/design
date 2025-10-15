package ru.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValues = container[index];
        container[index] = newValue;
        return oldValues;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValues = container[index];
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(container, index + 1, container, index, elementsToMove);
        }
        container[--size] = null;
        modCount++;
        return oldValues;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            private void checkForCoModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkForCoModification();
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

    private void grow() {
        int newCapacity = (container.length == 0) ? 1 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }
}