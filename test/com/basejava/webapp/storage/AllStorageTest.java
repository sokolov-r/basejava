package com.basejava.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamFileStorageTest.class,
        ObjectStreamPathStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        DataFileStorageTest.class,
        DataPathStorageTest.class,
        XmlPathStorageTest.class,
        JsonPathStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}
