package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private static final Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    public void doDelete(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void doUpdate(Resume r, String searchKey) {
        map.put(searchKey, r);
    }

    @Override
    protected ArrayList<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    public int size() {
        return map.size();
    }
}
