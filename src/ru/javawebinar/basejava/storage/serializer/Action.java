package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

interface Action<T> {
    void accept(T t) throws IOException;
}