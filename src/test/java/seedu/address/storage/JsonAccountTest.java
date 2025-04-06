package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Account;

public class JsonAccountTest {
    private static final String INVALID_USERNAME = null;
    private static final String INVALID_PASSWORD = null;

    private static final String VALID_USERNAME = "Jane Doe";
    private static final String VALID_PASSWORD = "12345";

    private static final Account VALID_ACCOUNT = new Account(VALID_USERNAME, VALID_PASSWORD);

    @Test
    public void toModelType_validAccount_returnsAccount() {
        JsonAccount jsonAccount = new JsonAccount(VALID_USERNAME, VALID_PASSWORD);
        Account testAccount;
        try {
            testAccount = jsonAccount.toModelType();
        } catch (IllegalValueException e) {
            testAccount = null;
        }
        assertEquals(VALID_ACCOUNT, testAccount);
    }

    @Test
    public void toModelType_invalidUsername_throwsIllegalValueException() {
        JsonAccount jsonAccount = new JsonAccount(INVALID_USERNAME, VALID_PASSWORD);
        assertThrows(IllegalValueException.class, () -> jsonAccount.toModelType());
    }

    @Test
    public void toModelType_invalidPassword_throwsIllegalValueException() {
        JsonAccount jsonAccount = new JsonAccount(VALID_USERNAME, INVALID_PASSWORD);
        assertThrows(IllegalValueException.class, () -> jsonAccount.toModelType());
    }
}
