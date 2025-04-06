package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;

public class JsonAccountBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonAccountBookStorageTest");
    private static final Path NULL_PATH = null;

    @TempDir
    public Path testFolder;

    @Test
    public void readAccountBook_null_throwsNullPointerException() {
        JsonAccountStorage accountStorage = new JsonAccountStorage(TEST_DATA_FOLDER);
        assertThrows(NullPointerException.class, () -> accountStorage.readAccountBook(NULL_PATH));
    }

    @Test
    public void readAccountBook_success() {
        JsonAccountStorage accountStorage = new JsonAccountStorage(TEST_DATA_FOLDER);
        Path validTestPath = Paths.get("ValidAccountBook.json");
        boolean result = true;
        try {
            accountStorage.readAccountBook(validTestPath);
        } catch (DataLoadingException e) {
            result = false;
        }
        assertEquals(true, result);
    }

}
