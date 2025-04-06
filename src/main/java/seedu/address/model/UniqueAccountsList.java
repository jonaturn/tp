package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.address.model.person.exceptions.DuplicateAccountException;

/**
 * A list of accounts that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Account#isSameAccount(Account)}. As such, adding and updating
 * of accounts uses Account#isSameAccount(Account) for equality so as to ensure that the person being added or updated
 * is unique in terms of identity in the UniqueAccountList. However, the removal of an account uses
 * Person#equals(Object) so as to ensure that the account with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 * <p>
 * Editing an account is not yet available
 *
 * @see Account#isSameAccount(Account)
 */
public class UniqueAccountsList implements Iterable<Account> {

    private ArrayList<Account> internalList = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Account toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAccount);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Account toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAccountException();
        }
        internalList.add(toAdd);
    }

    public ArrayList<Account> asUnmodifiableArrayList() {
        return internalList;
    }

    /**
     * Validates each account in the accounts list and ensures that each account is unique
     *
     * @param accounts an account list that should be unique
     */
    public void setAccounts(ArrayList<Account> accounts) {
        requireAllNonNull(accounts);
        if (!accountsAreUnique(accounts)) {
            throw new DuplicateAccountException();
        }
        internalList.clear();
        for (Account account : accounts) {
            internalList.add(account);
        }
    }

    /**
     * Copies another UniqueAccountsList accounts to this object.
     *
     * @param accounts other accounts list to be copied.
     */
    public void setAccounts(UniqueAccountsList accounts) {
        requireNonNull(accounts);
        internalList.clear();
        for (Account account : accounts) {
            internalList.add(account);
        }
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean accountsAreUnique(List<Account> accounts) {
        for (int i = 0; i < accounts.size() - 1; i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (accounts.get(i).isSameAccount(accounts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Iterator<Account> iterator() {
        return internalList.iterator();
    }
}
