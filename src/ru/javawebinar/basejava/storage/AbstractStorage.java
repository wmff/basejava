package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    AbstractStorage() {
        LOGGER.setLevel(Level.INFO);
    }

    public void save(Resume resume) {
        LOGGER.info("Save resume " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOGGER.info("Get by uuid=" + uuid);
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        LOGGER.info("Delete by uuid=" + uuid);
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        LOGGER.info("Update resume "+resume+" by uuid="+ uuid);
        SK searchKey = getExistSearchKey(uuid);
        doUpdate(resume, searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract List<Resume> doCopyAll();
}
