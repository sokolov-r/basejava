package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.StorageException;
import org.junit.Test;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword()));
    }

    @Override
    @Test(expected = StorageException.class)
    public void shouldThrowExistStorageExceptionWhenResumeExistWhileSave() {
        super.shouldThrowExistStorageExceptionWhenResumeExistWhileSave();
    }
}
