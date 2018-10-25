package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume r);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();
}
