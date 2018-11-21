package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {
    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    void saveInStorage(Resume resume, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(outputStream)){
            oos.writeObject(resume);
        }
    }

    @Override
    Resume loadFromStorage(InputStream inputStream) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
