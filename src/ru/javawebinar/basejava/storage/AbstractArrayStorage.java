package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doSave(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveInStorage(r, (int) searchKey);
            size++;
        }
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public void doDelete(Object searchKey) {
        deleteFromStorage((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size()));
    }

    public int size() {
        return size;
    }

    protected abstract void saveInStorage(Resume r, int index);

    protected abstract void deleteFromStorage(int index);
}