package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

interface Action<T> {
    void write(T t) throws IOException;
}