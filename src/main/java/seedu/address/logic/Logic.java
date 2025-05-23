package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CreateUserException;
import seedu.address.logic.commands.exceptions.InvalidAccessRightsException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.ReadOnlyAccountBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, InvalidAccessRightsException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Logs the user in
     */
    void logUserIn(String accountType);

    /**
     * Logs the user out
     */
    void logUserOut();

    ReadOnlyAccountBook getAccountBook();

    Path getAccountBookFilePath();

    ArrayList<Account> getFilteredAccountList();

    String addNewUser(Account account) throws CreateUserException, IOException;

    ArrayList<Account> getAccountList();
}
