package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Assert;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    public void save() {
        int i = storage.size();
        super.save();
        Assert.assertEquals("3", storage.getAll()[2].getUuid());
        Assert.assertEquals(i + 1, storage.size());
    }

    @Override
    public void getAll() {
        Resume[] arr = {r1, r2, r4};
        Assert.assertArrayEquals(arr, storage.getAll());
    }
}