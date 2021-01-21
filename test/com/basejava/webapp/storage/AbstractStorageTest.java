package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = Config.get().getStorageDir();

    Storage storage;

    private static final String UUID_1 = "1";
    private static final String UUID_2 = "2";
    private static final String UUID_3 = "3";
    private static final String UUID_4 = "4";
    private static final String NONAME = "NoName";

    private static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, NONAME);
    private static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, NONAME);
    private static final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, NONAME);
    private static final Resume RESUME_3NEW = ResumeTestData.createResume(UUID_3, NONAME);
    private static final Resume RESUME_4 = ResumeTestData.createResume(UUID_4, NONAME);

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
        storage.save(ResumeTestData.createResume(UUID_1, NONAME));
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
        Assert.assertEquals(RESUME_3NEW, storage.get(UUID_3));
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
        List<Resume> expectedResumes = new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(expectedResumes, list);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}