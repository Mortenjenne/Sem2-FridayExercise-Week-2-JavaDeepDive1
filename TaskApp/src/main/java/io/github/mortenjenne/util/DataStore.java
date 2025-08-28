package io.github.mortenjenne.util;

public interface DataStore<T> {
    String save(T obj);
    T load(String filename);
}
