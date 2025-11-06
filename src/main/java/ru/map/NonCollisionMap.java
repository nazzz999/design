package ru.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        int hash = hashCode;
        return hash ^ (hash >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        int oldCap = capacity;
        capacity = capacity << 1;
        MapEntry<K, V> [] old = table;
        table = new MapEntry[capacity];
        for (int i = 0; i < oldCap; i++) {
            MapEntry<K, V> cell = old[i];
            if (cell != null) {
                int index = indexFor(hash(Objects.hashCode(cell.key)));
                table[index] = cell;
            }
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        return index == -1 ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<K> {
        private final int expectedModCount = modCount;
        private int cursor = -1;
        private int nextIndex = advance(cursor);

        @Override
        public boolean hasNext() {
            checkForCountMod();
            return nextIndex != -1;
        }

        @Override
        public K next() {
            checkForCountMod();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = nextIndex;
            nextIndex = advance(cursor);
            return table[cursor].key;
        }

        private void checkForCountMod() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        private int advance(int from) {
            for (int i = from + 1; i < capacity; i++) {
                if (table[i] != null) {
                    return i;
                }
            }
            return -1;
        }
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int findIndex(Object key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> cell = table[index];
        if (cell == null) {
            return -1;
        }
        if (Objects.hashCode(cell.key) != Objects.hashCode(key)) {
            return -1;
        }
        if (!Objects.equals(cell.key, key)) {
            return -1;
        }
        return index;
    }
}
