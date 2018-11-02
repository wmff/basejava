package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object searchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(String uuid, Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);
}
