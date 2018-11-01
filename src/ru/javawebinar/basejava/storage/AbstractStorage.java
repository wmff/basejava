package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Integer index = getIndex(r.getUuid());

        if ((index != null) && (index >= 0)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, index);
        }
    }

    public Resume get(String uuid) {
        Integer index = getIndex(uuid);

        if ((index == null) || (index < 0)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid, index);
    }

    public void delete(String uuid) {
        Integer index = getIndex(uuid);

        if ((index == null) || (index < 0)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(uuid, index);
        }
    }

    public void update(Resume r) {
        Integer index = getIndex(r.getUuid());

        if ((index == null) || (index < 0)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, index);
        }
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void doSave(Resume r, Integer index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract void doUpdate(Resume r, int index);
}
