package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    static final int STORAGE_LIMIT = 10_000;

    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveInStorage(r, index);
            size++;
        }
    }

    @Override
    public Resume doGet(String uuid, int index) {
        return storage[index];
    }

    @Override
    public void doDelete(String uuid, int index) {
        deleteFromStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void doUpdate(Resume r, int index) {
        storage[index] = r;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void saveInStorage(Resume r, Integer index);

    protected abstract void deleteFromStorage(int index);
}