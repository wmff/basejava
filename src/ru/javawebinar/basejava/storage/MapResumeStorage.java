package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private static final Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public ArrayList<Resume> doGetAll(){
        return new ArrayList<>(map.values());
    }

    public int size() {
        return map.size();
    }
}
