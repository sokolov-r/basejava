package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Assert;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Override
    public void getAll() {
        Resume[] arr = {r1, r4, r2};
        Assert.assertArrayEquals(arr, storage.getAll());
    }
}