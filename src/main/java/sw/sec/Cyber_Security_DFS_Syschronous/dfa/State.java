package sw.sec.Cyber_Security_DFS_Syschronous.dfa;

public enum State {
    // Represents the initial state of the DFA
    START,
    // Represents the state after the username has been entered
    USERNAME_ENTERED,
    // Represents the state after the password has been entered
    PASSWORD_ENTERED,
    // Represents the state after credentials have been submitted
    CREDENTIALS_SUBMITTED,
    // Represents the state when the user is authenticated successfully
    AUTHENTICATED,
    // Represents the state when the authentication process has failed
    REJECTED
}
