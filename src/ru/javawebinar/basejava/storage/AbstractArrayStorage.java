package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (index >= 0) {
            printResumeError(r.getUuid(), " already exist");
        } else if (isStorageNotOverflow()) {
            if (index == -STORAGE_LIMIT) {
                saveInStorage(r, size);
            } else {
                saveInStorage(r, index);
            }
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            printResumeError(uuid, " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            printResumeError(uuid, " not exist");
        } else {
            deleteFromStorage(index);
            size--;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveInStorage(Resume r, int index);

    protected abstract void deleteFromStorage(int index);

    private void printResumeError(String uuid, String s) {
        System.out.println("Resume uuid=" + uuid + s);
    }

    private boolean isStorageNotOverflow() {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
            return false;
        } else {
            return true;
        }
    }
}