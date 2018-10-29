package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_5 = "uuid5";
    private static final Resume RESUME_5 = new Resume(UUID_5);
    private static final String UUID_6 = "uuid6";
    private static final Resume RESUME_6 = new Resume(UUID_6);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 5; i++) {
            Resume r = new Resume("uuid" + (i + 1));
            storage.save(r);
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
        Assert.assertEquals(RESUME_6, storage.get(RESUME_6.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_5);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow");
        }
        storage.save(RESUME_5);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_5, storage.get(RESUME_5.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_6.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_5.getUuid());
        checkSize(4);
        storage.get(RESUME_5.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_6.getUuid());
    }

    @Test
    public void update() {
        storage.update(RESUME_5);
        Assert.assertSame(RESUME_5, storage.get(RESUME_5.getUuid()));
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
            Resume r = new Resume("uuid" + (i + 1));
            testResume[i] = r;
        }
        checkSize(5);
        Assert.assertArrayEquals(allResume, testResume);
    }

    @Test
    public void size() {
        checkSize(5);
    }

    private void checkSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}