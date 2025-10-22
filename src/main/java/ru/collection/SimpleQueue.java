package ru.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int size;
    private int inCount;
    private int outCount;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outCount == 0) {
            for (int i = 0; i < inCount; i++) {
                output.push(input.pop());
            }
            outCount = inCount;
            inCount = 0;
        }
        size--;
        outCount--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        size++;
        inCount++;
    }

    public int size() {
        return size;
    }
}
