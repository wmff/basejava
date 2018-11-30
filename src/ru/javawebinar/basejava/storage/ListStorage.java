package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListStorage extends AbstractStorage<Integer> {
    private static final List<Resume> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return IntStream.range(0, list.size()).filter(i -> list.get(i).getUuid().equals(uuid)).findFirst().orElse(-1);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != -1;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    public void doDelete(Integer searchKey) {
        list.remove((int) searchKey);
    }

    @Override
    public void doUpdate(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    public int size() {
        return list.size();
    }
}
