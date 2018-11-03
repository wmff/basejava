package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private static final Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        //noinspection SuspiciousMethodCalls
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        //noinspection SuspiciousMethodCalls
        return map.get(searchKey);
    }

    @Override
    public void doDelete(Object searchKey) {
        //noinspection SuspiciousMethodCalls
        map.remove(searchKey);
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    public Resume[] getAll() {
        Resume[] resumeArray = map.values().toArray(new Resume[size()]);
        Arrays.sort(resumeArray);
        return resumeArray;
    }

    public int size() {
        return map.size();
    }
}
