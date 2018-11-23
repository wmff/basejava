package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface StreamSerializable {
    void saveInStorage(Resume resume, OutputStream outputStream) throws IOException;

    Resume loadFromStorage(InputStream inputStream) throws IOException;
}
