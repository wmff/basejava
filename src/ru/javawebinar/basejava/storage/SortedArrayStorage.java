package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveInStorage(Resume r, int index) {
        int absIndex = Math.abs(index);

        System.arraycopy(storage, absIndex - 1, storage, absIndex, size - absIndex + 1);
        storage[absIndex - 1] = r;
    }

    @Override
    protected void deleteFromStorage(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}