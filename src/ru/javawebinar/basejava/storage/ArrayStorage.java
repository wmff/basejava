package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        return IntStream.range(0, size).filter(i -> uuid.equals(storage[i].getUuid())).findFirst().orElse(-1);
    }

    @Override
    protected void saveInStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage[index] = storage[size - 1];
    }
}