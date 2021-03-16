package data;

import project.data.DataStorage;
import project.data.InMemoryDataStorage;

public class InMemoryDataStorageTest extends AbstractDataStorageTest {
    @Override
    public DataStorage createDataStorage() {
        return new InMemoryDataStorage();
    }
}