package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateAccountException;

public class UniqueAccountListTest {
    private final UniqueAccountsList uniqueAccountsList = new UniqueAccountsList();
    private final ArrayList<Account> testAccountsList = new ArrayList<>();

    @Test
    public void contains_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountsList.contains(null));
    }

    @Test
    public void contains_accountNotInList_returnsFalse() {
        assertFalse(uniqueAccountsList.contains(new Account("Jane Doe", "12345")));
    }

    @Test
    public void contains_accountInList_returnsTrue() {
        uniqueAccountsList.add(new Account("Jane Doe", "12345"));
        assertTrue(uniqueAccountsList.contains(new Account("Jane Doe", "12345")));
    }

    @Test
    public void add_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountsList.add(null));
    }

    @Test
    public void add_duplicateAccount_throwsDuplicateAccountException() {
        uniqueAccountsList.add(new Account("Jane Doe", "12345"));
        assertThrows(DuplicateAccountException.class, () -> uniqueAccountsList.add(
                new Account("Jane Doe", "12345")));
    }

    @Test
    public void setAccount_nullTargetAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountsList.setAccounts((UniqueAccountsList) null));
    }

    @Test
    public void setAccount_success() {
        UniqueAccountsList expectedUniqueAccountsList = new UniqueAccountsList();
        Account janeDoe = new Account("Jane Doe", "12345");
        expectedUniqueAccountsList.add(janeDoe);
        uniqueAccountsList.setAccounts(expectedUniqueAccountsList);
        boolean result = uniqueAccountsList.contains(janeDoe);
        assertTrue(result);
    }

}
