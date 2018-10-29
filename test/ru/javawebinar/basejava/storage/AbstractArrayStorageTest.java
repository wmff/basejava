package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
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
    public void size() {
        checkSize(5);
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid1");
        storage.update(r);
        Assert.assertSame(r, storage.get("uuid1"));

    }

    @Test
    public void getAll() {
        Resume[] allResume = storage.getAll();
        for (int i = 0; i < allResume.length; i++) {
            Assert.assertEquals(new Resume("uuid" + (i + 1)), allResume[i]);
        }
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid6");
        storage.save(r);
        checkSize(6);
        Assert.assertEquals(r, storage.get("uuid6"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid5"));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 6; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        checkSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid10");
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume("uuid1"), storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    private void checkSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}