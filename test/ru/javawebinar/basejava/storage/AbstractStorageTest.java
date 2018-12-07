package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    static final File STORAGE_DIR = Config.get().getStorageDir();
    final Storage storage;

    private static final Resume RESUME_1 = ResumeTestData.RESUME_1;
    private static final Resume RESUME_2 = ResumeTestData.RESUME_2;
    private static final Resume RESUME_3 = ResumeTestData.RESUME_3;
    static final Resume RESUME_4 = ResumeTestData.RESUME_4;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        checkSize(4);
        checkEquals(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_3);
    }

    @Test
    public void get() {
        checkEquals(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_4.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_3.getUuid());
        checkSize(2);
        storage.get(RESUME_3.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_4.getUuid());
    }

    @Test
    public void update() {
        storage.update(RESUME_2);
        assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResume = storage.getAllSorted();
        assertEquals(actualResume.size(), actualResume.size());
        List<Resume> expectedResume = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(expectedResume);
        assertEquals(expectedResume, actualResume);
    }

    @Test
    public void size() {
        checkSize(3);
    }

    private void checkEquals(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void checkSize(int size) {
        assertEquals(size, storage.size());
    }
}
