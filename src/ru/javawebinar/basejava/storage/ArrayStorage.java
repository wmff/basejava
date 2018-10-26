package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            printResumeError(r.getUuid(), " already exist");
        } else if (isStorageNotOverflow()) {
            storage[size] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            printResumeError(uuid, " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}