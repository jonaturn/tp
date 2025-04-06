package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CreateUserCommand.MESSAGE_BLANK_FIELDS;
import static seedu.address.logic.commands.CreateUserCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CreateUserCommand.MESSAGE_WHITESPACE;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CreateUserException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.ReadOnlyAccountBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

public class CreateUserCommandTest {
    private Logic logic = new LogicStub();

    @Test
    public void createUserCommand_validInputs_success() {
        String reply;
        try {
            CreateUserCommand createUser = new CreateUserCommand("Admin", "Admin123", logic);
            reply = createUser.createNewUser();
        } catch (CreateUserException | IOException e) {
            reply = e.getMessage();
        }
        assertEquals(reply, MESSAGE_SUCCESS);
    }

    @Test
    public void createUserCommand_whitespacePassword_failure() {
        String reply;
        try {
            CreateUserCommand createUser = new CreateUserCommand("Admin", "Admin 123", logic);
            reply = createUser.createNewUser();
        } catch (CreateUserException | IOException e) {
            reply = e.getMessage();
        }
        assertEquals(reply, MESSAGE_WHITESPACE);
    }

    @Test
    public void createUserCommand_whitespaceUsername_failure() {
        String reply;
        try {
            CreateUserCommand createUser = new CreateUserCommand("Admin", "Admin 123", logic);
            reply = createUser.createNewUser();
        } catch (CreateUserException | IOException e) {
            reply = e.getMessage();
        }
        assertEquals(reply, MESSAGE_WHITESPACE);
    }

    @Test
    public void createUserCommand_nullPassword_failure() {
        String reply;
        try {
            CreateUserCommand createUser = new CreateUserCommand("Admin", "", logic);
            reply = createUser.createNewUser();
        } catch (CreateUserException | IOException e) {
            reply = e.getMessage();
        }
        assertEquals(reply, MESSAGE_BLANK_FIELDS);
    }

    @Test
    public void createUserCommand_nullUsername_failure() {
        String reply;
        try {
            CreateUserCommand createUser = new CreateUserCommand("", "Admin123", logic);
            reply = createUser.createNewUser();
        } catch (CreateUserException | IOException e) {
            reply = e.getMessage();
        }
        assertEquals(reply, MESSAGE_BLANK_FIELDS);
    }

    private class LogicStub implements Logic {
        @Override
        public CommandResult execute(String commandText) throws CommandException, ParseException {
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        @Override
        public void logUserIn(String accountType) {

        }

        @Override
        public ReadOnlyAccountBook getAccountBook() {
            return null;
        }

        @Override
        public Path getAccountBookFilePath() {
            return null;
        }

        @Override
        public ArrayList<Account> getFilteredAccountList() {
            return null;
        }

        @Override
        public String addNewUser(Account toAdd) throws CreateUserException, IOException {

            if (toAdd.getUsername().contains(" ") || toAdd.getPassword().contains(" ")) {
                throw new CreateUserException(MESSAGE_WHITESPACE);
            }

            if (toAdd.getUsername().isBlank() || toAdd.getPassword().isBlank()) {
                throw new CreateUserException(MESSAGE_BLANK_FIELDS);
            }

            return MESSAGE_SUCCESS;

        }

        @Override
        public ArrayList<Account> getAccountList() {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(new Account("ITstaff", "ITstaff@123"));
            return accounts;
        }
    }
}
