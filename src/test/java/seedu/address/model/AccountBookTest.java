package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AccountBookTest {
    private AccountBook accountBook = new AccountBook();

    @Test
    public void resetAccount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accountBook.resetAccounts(null));
    }

    @Test
    public void resetAccounts_success() {
        AccountBook newAccountBook = new AccountBook();
        newAccountBook.addAccount(new Account("John Doe", "12345"));
        accountBook.resetAccounts(newAccountBook);
        boolean result = true;
        int count = 0;
        for (Account account : accountBook.getAccountList()) {
            if (!account.isSameAccount(newAccountBook.getAccountList().get(count))) {
                result = false;
            }
            count++;
        }
        assertEquals(true, result);
    }

    @Test
    public void hasAccount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accountBook.hasAccount(null));
    }

    @Test
    public void hasAccount_success() {
        AccountBook newAccountBook = new AccountBook();
        newAccountBook.addAccount(new Account("John Doe", "12345"));
        boolean result = newAccountBook.hasAccount(new Account("John Doe", "12345"));
        assertEquals(true, result);
    }

    @Test
    public void hasAccount_failure() {
        AccountBook newAccountBook = new AccountBook();
        newAccountBook.addAccount(new Account("John Doe", "12345"));
        boolean result = accountBook.hasAccount(new Account("Jane Doe", "12345"));
        assertEquals(false, result);
    }

    @Test
    public void getAccountList_success() {
        accountBook.addAccount(new Account("John Doe", "12345"));
        accountBook.addAccount(new Account("Jane Doe", "12345"));
        ArrayList<Account> newAccountList = accountBook.getAccountList();
        boolean result = true;
        int count = 0;
        for (Account account : accountBook.getAccountList()) {
            if (!account.isSameAccount(newAccountList.get(count))) {
                result = false;
            }
            count++;
        }
        assertEquals(true, result);
    }
}
