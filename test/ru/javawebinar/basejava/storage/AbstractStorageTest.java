package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    final Storage storage;

    private static final String UUID_5 = "uuid5";
    private static final String FULL_NAME = "full name";
    static final Resume RESUME_5 = new Resume(UUID_5, FULL_NAME);
    private static final String UUID_6 = "uuid6";
    private static final Resume RESUME_6 = new Resume(UUID_6, FULL_NAME);

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 5; i++) {
            storage.save(new Resume("uuid" + (i + 1), FULL_NAME));
        }
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_6);
        checkSize(6);
        checkEquals(RESUME_6);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_5);
    }

    @Test
    public void get() {
        checkEquals(RESUME_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_6.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_5);
        checkSize(4);
        storage.get(UUID_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_6.getUuid());
    }

    @Test
    public void update() {
        storage.update(RESUME_5);
        assertSame(RESUME_5, storage.get(RESUME_5.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_6);
    }

    @Test
    public void getAll() {
        List<Resume> allResume = storage.getAllSorted();
        List<Resume> testResume = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testResume.add(new Resume("uuid" + (i + 1), FULL_NAME));
        }
        assertEquals(testResume.size(), allResume.size());
//        assertArrayEquals(allResume, testResume);
        assertEquals(testResume, allResume);
    }

    @Test
    public void size() {
        checkSize(5);
    }

    private void checkEquals(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void checkSize(int size) {
        assertEquals(size, storage.size());
    }
}
