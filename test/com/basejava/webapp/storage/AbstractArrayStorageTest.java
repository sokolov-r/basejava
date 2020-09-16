package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    Storage storage;
    Resume r1 = new Resume("1");
    Resume r2 = new Resume("2");
    Resume r4 = new Resume("4");
    Resume r4New = new Resume("4");
    Resume r5 = new Resume("5");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r4);
        storage.save(r2);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(new Resume("3"));
    }

    @Test
    public void saveWithStorageException() {
        while (storage.size() < 10000) {
            storage.save(new Resume());
        }
        try {
            storage.save(new Resume());
            Assert.fail("Overflow without exception");
        } catch (StorageException e) {
            Assert.assertEquals("Storage overflow", e.getMessage());
        }
    }

    @Test
    public void saveWithExistStorageException() {
        try {
            storage.save(new Resume("1"));
            Assert.fail("save without exception");
        } catch (StorageException e) {
            Assert.assertEquals("Resume 1 already exist", e.getMessage());
        }
    }

    @Test
    public void get() {
        Assert.assertEquals(r4, storage.get("4"));
    }

    @Test
    public void getWithNotExistStorageException() {
        try {
            storage.get("0");
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 0 not exist", e.getMessage());
        }
    }

    @Test
    public void update() {
        storage.update(r4New);
        Assert.assertEquals(r4New, storage.get("4"));
    }

    @Test
    public void updateWithNotExistStorageException() {
        try {
            storage.update(r5);
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 5 not exist", e.getMessage());
        }
    }

    @Test
    public void delete() {
        storage.delete("1");
        Assert.assertEquals("2", storage.getAll()[0].getUuid());
    }

    @Test
    public void deleteWithNotExistStorageException() {
        try {
            storage.delete("6");
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 6 not exist", e.getMessage());
        }
    }

    @Test
    public void getAll() {
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}