package sw.sec.Cyber_Security_DFS_Syschronous.dfa;


import java.util.Map;

/***
 * A simple DFA for a login process.
 * The DFA transitions through states based on input symbols representing user actions.
 * It can be used to validate a login sequence.
 */
public class LoginDFA {

    // Represents the states of the DFA
    private State currentState = State.START;

    // Maps each state to its possible transitions based on input symbols
    private static final Map<State, Map<InputSymbol, State>> TRANSITIONS =
            Map.of(
            State.START, Map.of(InputSymbol.ENTER_USERNAME, State.USERNAME_ENTERED),
            State.USERNAME_ENTERED, Map.of(InputSymbol.ENTER_PASSWORD, State.PASSWORD_ENTERED),
            State.PASSWORD_ENTERED, Map.of(InputSymbol.SUBMIT, State.CREDENTIALS_SUBMITTED),
            State.CREDENTIALS_SUBMITTED, Map.of(
                    InputSymbol.VALID_SUCCESS, State.AUTHENTICATED,
                    InputSymbol.VALID_FAIL,    State.REJECTED
            )
    );

    // Returns the current state of the DFA
    public State getCurrentState() {
        return currentState;
    }

    // Processes an input symbol and transitions to the next state if valid.
    public void process(InputSymbol symbol) {
        var map = TRANSITIONS.get(currentState);
        if (map != null && map.containsKey(symbol)) {
            currentState = map.get(symbol);
        } else {
            throw new IllegalStateException(
                    "Transition error: no valid transition for symbol= " + symbol );

        }

    }


    public boolean isInAcceptState() {
        return currentState == State.AUTHENTICATED || currentState == State.REJECTED;
    }

}
