package seedu.address.logic.commands;

import static seedu.address.commons.util.HasherUtil.hashPassword;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.AuthenticateException;
import seedu.address.model.Account;

/**
 * Authenticates the user
 */
public class AuthenticateCommand {
    public static final String MESSAGE_SUCCESS_ADMIN = "Authenticated User: Admin Logged In.";
    public static final String MESSAGE_SUCCESS_IT = "Authenticated User: IT Staff Logged In.";
    public static final String MESSAGE_FAILURE = "Login Failed. Invalid username or password.";

    private static final String username = "Admin";
    private static final String password = "0cuBNQPBLulTdrCSw2kNe2fvE0lTxHDYv73p07Zy9nc=";
    private static final String salt = "CS2103T";

    private final Logger logger = LogsCenter.getLogger(AuthenticateCommand.class);

    private final String usernameInput;
    private final String passwordInput;

    /**
     * Constructor that takes in username and password from LoginDialog stage
     *
     * @param username username field
     * @param password password field
     */
    public AuthenticateCommand(String username, String password) {
        this.usernameInput = username;
        this.passwordInput = password;
    }

    /**
     * Attempt to authenticate user
     *
     * @param logic LogicManager for MainWindow
     * @throws AuthenticateException exception for authenticate failure
     */
    public String authenticateUser(Logic logic) throws AuthenticateException {

        ArrayList<Account> accountsIT = retrieveAccount(logic);

        if (username.equals(usernameInput)
                && password.equals(hashPassword(passwordInput, salt))) {
            //change logicManager isLoggedIn to true
            logger.info("Logged Admin in.");
            logic.logUserIn("Admin");
            return MESSAGE_SUCCESS_ADMIN;
        } else {
            //IT staff login
            if (!accountsIT.isEmpty()) {
                for (Account account : accountsIT) {
                    if (account.getUsername().equals(usernameInput) && account.getPassword().equals(passwordInput)) {
                        logger.info(account.getUsername() + " " + account.getPassword());
                        logic.logUserIn("IT");
                        return MESSAGE_SUCCESS_IT;
                    }
                }
            }
            //return error message on ui
            throw new AuthenticateException(MESSAGE_FAILURE);
        }

    }

    /**
     * Retrieves account list from logic
     */
    public ArrayList<Account> retrieveAccount(Logic logic) {
        logger.info("Retrieving accounts from the logic");
        ArrayList<Account> accounts = logic.getAccountList();
        logger.info(accounts.get(0).getUsername() + " " + accounts.get(0).getPassword());
        return logic.getAccountList();
    }
}
