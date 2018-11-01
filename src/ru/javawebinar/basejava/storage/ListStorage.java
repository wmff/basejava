package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        list.add(r);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return list.get(index);
    }

    @Override
    public void doDelete(String uuid, int index) {
        list.remove(index);
    }

    @Override
    public void doUpdate(Resume r, int index) {
        list.set(index, r);
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    public int size() {
        return list.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
