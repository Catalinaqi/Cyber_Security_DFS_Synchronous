package sw.sec.Cyber_Security_DFS_Syschronous.service;

import sw.sec.Cyber_Security_DFS_Syschronous.dfa.InputSymbol;
import sw.sec.Cyber_Security_DFS_Syschronous.dfa.LoginDFA;
import sw.sec.Cyber_Security_DFS_Syschronous.dfa.State;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationService is responsible for handling user authentication.
 * It uses a Deterministic Finite Automaton (DFA) to manage the login process.
 * The service checks user credentials against a simulated user store.
 */
@Service
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    // Simulated user store for authentication
    private final Map<String, String> userStore = new HashMap<>();

    // Constructor to initialize the user store with a sample user
    public AuthenticationService() {
        // Adding a sample user for demonstration purposes
        userStore.put("admin", "password");
    }

    /**
     * Authenticates a user based on the provided username and password.
     * This method simulates a login process using a Deterministic Finite Automaton (DFA).
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The password of the user attempting to authenticate.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {

        log.info("Authentication attempt for user='{}'", username);

        LoginDFA dfa = new LoginDFA();

        // Check if the username exists in the user store
        dfa.process(InputSymbol.ENTER_USERNAME);

        // If the username is not found, the DFA will transition to a state that indicates failure
        dfa.process(InputSymbol.ENTER_PASSWORD);

        // Check if the password matches the stored password for the username
        dfa.process(InputSymbol.SUBMIT);

        // Validate the credentials against the user store
        boolean valid = password.equals(userStore.get(username));
        log.info("Credential check for user='{}': {}", username, valid ? "SUCCESS" : "FAIL");
        // Process the result of the credential validation
        dfa.process(valid ? InputSymbol.VALID_SUCCESS : InputSymbol.VALID_FAIL);

        // Check the final state of the DFA to determine if authentication was successful
        if (dfa.getCurrentState() == State.AUTHENTICATED) {
            log.info("User '{}' authenticated successfully", username);
            return true;
        } else {
            log.warn("User '{}' failed authentication", username);
            return false;
        }
    }

}
