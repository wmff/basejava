package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private static final Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Resume searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    public void doDelete(Resume searchKey) {
        map.remove(searchKey.getUuid());
    }

    @Override
    public void doUpdate(Resume r, Resume searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public ArrayList<Resume> doCopyAll(){
        return new ArrayList<>(map.values());
    }

    public int size() {
        return map.size();
    }
}
