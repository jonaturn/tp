package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
    @Test
    public void testAccount_equals_success() {
        Account account1 = new Account("John Doe", "john");
        Account account2 = new Account("John Doe", "john");
        boolean result = account1.equals(account2);
        assertEquals(true, result);
    }

    @Test
    public void testAccountInvalidUsername_equals_failure() {
        Account account1 = new Account("John Doe", "john");
        Account account2 = new Account("John Cheng", "john");
        boolean result = account1.equals(account2);
        assertEquals(false, result);
    }

    @Test
    public void testAccountInvalidPassword_equals_failure() {
        Account account1 = new Account("John Doe", "john");
        Account account2 = new Account("John Doe", "john1");
        boolean result = account1.equals(account2);
        assertEquals(false, result);
    }

    @Test
    public void test_isSameAccount_true() {
        Account account1 = new Account("John Doe", "john");
        Account account2 = new Account("John Doe", "john");
        boolean result = account1.isSameAccount(account2);
        assertEquals(true, result);
    }

    @Test
    public void test_isSameAccount_false() {
        Account account1 = new Account("John Doe", "john");
        Account account2 = new Account("John Does", "john1");
        assertEquals(false, account1.isSameAccount(account2));
    }
}
