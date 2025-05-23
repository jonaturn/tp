package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AccountBook;
import seedu.address.model.ReadOnlyAccountBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private AccountBookStorage accountBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage}
     * and {@code AccountBookStorage}.
     */
    public StorageManager(
            AddressBookStorage addressBookStorage,
            UserPrefsStorage userPrefsStorage,
            AccountBookStorage accountBookStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.accountBookStorage = accountBookStorage;
    }

    public StorageManager(Path addressBookFilePath, Path userPrefsFilePath) {
        this(new JsonAddressBookStorage(addressBookFilePath), new JsonUserPrefsStorage(userPrefsFilePath));
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ AccountBook methods ==============================

    @Override
    public Path getAccountBookFilePath() {
        return accountBookStorage.getAccountBookFilePath();
    }

    @Override
    public Optional<AccountBook> readAccountBook() throws DataLoadingException {
        return readAccountBook(accountBookStorage.getAccountBookFilePath());
    }

    @Override
    public Optional<AccountBook> readAccountBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read accounts from file: " + filePath);
        return accountBookStorage.readAccountBook(filePath);
    }

    @Override
    public void saveAccountBook(ReadOnlyAccountBook accountBook) throws IOException {
        saveAccountBook(accountBook, accountBookStorage.getAccountBookFilePath());
    }

    @Override
    public void saveAccountBook(ReadOnlyAccountBook accountBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to account file: " + filePath);
        accountBookStorage.saveAccountBook(accountBook, filePath);
    }

}
