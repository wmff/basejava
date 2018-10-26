package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (index >= 0) {
            printResumeError(r.getUuid(), " already exist");
        } else if (isStorageNotOverflow()) {
            int absIndex = Math.abs(index);
            System.arraycopy(storage, absIndex - 1, storage, absIndex, size - absIndex + 1);
            storage[absIndex - 1] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            printResumeError(uuid, " not exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}