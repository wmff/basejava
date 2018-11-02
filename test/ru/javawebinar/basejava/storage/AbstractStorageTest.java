package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    final Storage storage;

    protected static final String UUID_5 = "uuid5";
    protected static final Resume RESUME_5 = new Resume(UUID_5);
    protected static final String UUID_6 = "uuid6";
    protected static final Resume RESUME_6 = new Resume(UUID_6);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 5; i++) {
            storage.save(new Resume("uuid" + (i + 1)));
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
        Resume[] allResume = storage.getAll();
        Resume[] testResume = new Resume[5];
        for (int i = 0; i < 5; i++) {
            testResume[i] = new Resume("uuid" + (i + 1));
        }
        assertEquals(testResume.length, allResume.length);
        assertArrayEquals(allResume, testResume);
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