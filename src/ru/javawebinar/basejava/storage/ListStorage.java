package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void save(Resume r) {
        list.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Resume r) {
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return list.size();
    }
}
