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
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return list.get((int) searchKey);
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        list.remove((int) searchKey);
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        list.set((int) searchKey, r);
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    public int size() {
        return list.size();
    }
}
