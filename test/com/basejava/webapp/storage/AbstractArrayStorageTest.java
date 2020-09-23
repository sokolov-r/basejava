package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
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
    Resume r3 = new Resume("3");
    Resume r4 = new Resume("4");
    Resume r4New = new Resume("4");

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
        Assert.assertEquals(r3, storage.get("3"));
        Assert.assertEquals(i + 1, storage.size());
    }

    @Test
    public void shouldThrowStorageExceptionWhenStorageOverflowWhileSave() {
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

    @Test(expected = ExistStorageException.class)
    public void shouldThrowExistStorageExceptionWhenResumeExistWhileSave() {
        storage.save(new Resume("1"));
        Assert.fail("save without exception");
    }

    @Test
    public void get() {
        Assert.assertEquals(r4, storage.get("4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileGet() {
        storage.get("0");
        Assert.fail("Continues without NotExistStorageException");
    }

    @Test
    public void update() {
        storage.update(r4New);
        Assert.assertEquals(r4New, storage.get("4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileUpdate() {
        storage.update(r3);
        Assert.fail("Continues without NotExistStorageException");
    }

    @Test
    public void delete() {
        storage.delete("1");
        Assert.assertEquals(r2, storage.getAll()[0]);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileDelete() {
        storage.delete("6");
        Assert.fail("Continues without NotExistStorageException");
    }

    @Test
    public void getAll() {
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}