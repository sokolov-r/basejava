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
    Resume r3 = new Resume("3");

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
        int i = storage.size();
        storage.save(r3);
        Assert.assertEquals(i + 1, storage.size());
    }

    @Test
    public void shouldThrowWithStorageExceptionWhenSave() {
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
    public void shouldThrowWithExistStorageExceptionWhenSave() {
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
    public void shouldThrowWithNotExistStorageExceptionWhenGet() {
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
    public void shouldThrowWithNotExistStorageExceptionWhenUpdate() {
        try {
            storage.update(r3);
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 3 not exist", e.getMessage());
        }
    }

    @Test
    public void delete() {
        storage.delete("1");
        Assert.assertEquals(r2, storage.getAll()[0]);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void shouldThrowWithNotExistStorageExceptionWhenDelete() {
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