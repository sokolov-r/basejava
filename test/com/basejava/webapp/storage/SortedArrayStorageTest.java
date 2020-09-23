package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Assert;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    public void getAll() {
        Resume[] arr = {r1, r2, r4};
        Assert.assertArrayEquals(arr, storage.getAll());
    }
}