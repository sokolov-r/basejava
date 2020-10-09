package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    Storage storage;

    private static final String UUID_1 = "1";
    private static final String UUID_2 = "2";
    private static final String UUID_3 = "3";
    private static final String UUID_4 = "4";
    private static final String NONAME = "NoName";

    private static final Resume RESUME_1 = new Resume(UUID_1, NONAME);
    private static final Resume RESUME_2 = new Resume(UUID_2, NONAME);
    private static final Resume RESUME_3 = new Resume(UUID_3, NONAME);
    private static final Resume RESUME_3NEW = new Resume(UUID_3, NONAME);
    private static final Resume RESUME_4 = new Resume(UUID_4, NONAME);

    public AbstractStorageTest(Storage storage) {
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
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        int i = storage.size();
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
        Assert.assertEquals(i + 1, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void shouldThrowExistStorageExceptionWhenResumeExistWhileSave() {
        storage.save(new Resume(UUID_1, NONAME));
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileGet() {
        storage.get("0");
    }

    @Test
    public void update() {
        storage.update(RESUME_3NEW);
        Assert.assertSame(RESUME_3NEW, storage.get(UUID_3));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileUpdate() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldThrowNotExistStorageExceptionWhenResumeNotExistWhileDelete() {
        storage.delete("6");
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(RESUME_1);
        expectedResumes.add(RESUME_2);
        expectedResumes.add(RESUME_3);
        List<Resume> list = storage.getAllSorted();
        Assert.assertSame(expectedResumes.get(0), list.get(0));
        Assert.assertSame(expectedResumes.get(1), list.get(1));
        Assert.assertSame(expectedResumes.get(2), list.get(2));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}