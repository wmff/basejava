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
    protected void doSave(Resume r, Integer index) {
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(String uuid, int index) {
        return map.get(uuid);
    }

    @Override
    public void doDelete(String uuid, int index) {
        map.remove(uuid);
    }

    @Override
    public void doUpdate(Resume r, int index) {
        map.put(r.getUuid(), r);
    }

    public Resume[] getAll() {
        Resume[] resumeArray = map.values().toArray(new Resume[size()]);
        Arrays.sort(resumeArray);
        return resumeArray;
    }

    public int size() {
        return map.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
        return (map.containsKey(uuid)) ? 1 : null;
    }
}
