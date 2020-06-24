package com.basejava.webapp.storage;

import com.basejava.webapp.exception.*;
import com.basejava.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    protected Storage storage;
    protected Resume r1 = new Resume("1");
    protected Resume r2 = new Resume("2");
    protected Resume r4 = new Resume("4");
    protected Resume r4New = new Resume("4");
    protected Resume r5 = new Resume("5");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r4);
        storage.save(r2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(new Resume("3"));
        while (storage.size() < 4) {
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
    public void get() {
        Assert.assertEquals(r4, storage.get("4"));
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
        try {
            storage.update(r5);
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 5 not exist", e.getMessage());
        }
    }

    @Test
    public void delete() {
        storage.delete("2");
        try {
            storage.delete("2");
            Assert.fail("Continues without NotExistStorageException");
        } catch (NotExistStorageException e) {
            Assert.assertEquals("Resume 2 not exist", e.getMessage());
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