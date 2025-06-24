package sw.sec.Cyber_Security_DFS_Syschronous.controller;

import sw.sec.Cyber_Security_DFS_Syschronous.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class LoginController {

    // Logger for this class
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    // Authentication service to handle login logic
    private final AuthenticationService authService;

    /**
     * Constructor for LoginController.
     * Initializes the controller with the provided AuthenticationService.
     *
     * @param authService The authentication service to be used for login operations.
     */
    public LoginController(AuthenticationService authService) {
        this.authService = authService;
    }

    /**
     * Represents a request to log in with a username and password.
     * This record is used to encapsulate the login credentials.
     */

    public record LoginRequest(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        log.info("Received login POST for user='{}'", req.username());
        boolean ok = authService.authenticate(req.username(), req.password());
        if (ok) {
            log.debug("Responding 200 OK to user='{}'", req.username());
            return ResponseEntity.ok("Login successful");
        } else {
            log.debug("Responding 401 Unauthorized to user='{}'", req.username());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
