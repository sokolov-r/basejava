package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void shouldThrowStorageExceptionWhenStorageOverflowWhileSave() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("NoName"));
            }
        } catch (StorageException e) {
            Assert.fail("Overflow < LIMIT");
        }
        storage.save(new Resume("NoName"));
    }
}