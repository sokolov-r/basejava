package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    Storage storage;
    Resume r1 = new Resume("1");
    Resume r2 = new Resume("2");
    Resume r3 = new Resume("3");
    Resume r3New = new Resume("3");
    Resume r4 = new Resume("4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        int i = storage.size();
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("4"));
        Assert.assertEquals(i + 1, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void shouldThrowExistStorageExceptionWhenResumeExistWhileSave() {
        storage.save(new Resume("1"));
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get("1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileGet() {
        storage.get("0");
    }

    @Test
    public void update() {
        storage.update(r3New);
        Assert.assertEquals(r3New, storage.get("3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileUpdate() {
        storage.update(r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("1");
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(r1, storage.get("1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileDelete() {
        storage.delete("6");
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = {r1, r2, r3};
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}