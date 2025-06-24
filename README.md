# Login Application with Synchronous DFA (Java 17 + Spring Boot)

This project implements a login system driven by a **Deterministic Finite Automaton (DFA)**. The DFA operates **synchronously** to enforce a clear, step-by-step authentication flow.

---

## Table of Contents

1. [Introduction](#introduction)
2. [DFA Design and Analysis](#dfa-design-and-analysis)
3. [Key Concepts of Synchronous DFA](#key-concepts-of-synchronous-dfa)
4. [Authentication Flow](#authentication-flow)
5. [Usage](#usage)
6. [Next Steps & Extensions](#next-steps--extensions)

---

## Introduction

The goal of this project is to demonstrate a login mechanism controlled by a **synchronous** DFA. Each input symbol advances the automaton immediately, guaranteeing a deterministic and testable sequence of authentication steps.

---

## DFA Design and Analysis

Formal definition of the DFA:

$M = (Q, Σ, δ, q₀, F)$

* **Q (States):**

    * `START`
    * `USERNAME_ENTERED`
    * `PASSWORD_ENTERED`
    * `CREDENTIALS_SUBMITTED`
    * `AUTHENTICATED` (accept)
    * `REJECTED` (accept)

* **Σ (Alphabet):**

    * `ENTER_USERNAME`
    * `ENTER_PASSWORD`
    * `SUBMIT`
    * `VALID_SUCCESS`
    * `VALID_FAIL`

* **δ (Transition Function):**

  | From State              | Input Symbol    | To State                |
    | ----------------------- | --------------- | ----------------------- |
  | `START`                 | ENTER\_USERNAME | `USERNAME_ENTERED`      |
  | `USERNAME_ENTERED`      | ENTER\_PASSWORD | `PASSWORD_ENTERED`      |
  | `PASSWORD_ENTERED`      | SUBMIT          | `CREDENTIALS_SUBMITTED` |
  | `CREDENTIALS_SUBMITTED` | VALID\_SUCCESS  | `AUTHENTICATED`         |
  | `CREDENTIALS_SUBMITTED` | VALID\_FAIL     | `REJECTED`              |

* **q₀ (Initial State):** `START`

* **F (Accept States):** `{AUTHENTICATED, REJECTED}`

---

## Key Concepts of Synchronous DFA

* **Determinism:** Each (state, symbol) pair maps to exactly one next state.
* **Synchronous Processing:** `process(symbol)` immediately updates the state—no delays or asynchronous handling.
* **Accepting States:** Indicate final outcome: success or failure.
* **Error Handling:** Invalid transitions throw an exception.

---

## Authentication Flow

1. **START** → Username submission (`ENTER_USERNAME`)
2. **USERNAME\_ENTERED** → Password submission (`ENTER_PASSWORD`)
3. **PASSWORD\_ENTERED** → Submit credentials (`SUBMIT`)
4. **CREDENTIALS\_SUBMITTED** → Validation result (`VALID_SUCCESS` or `VALID_FAIL`)
5. **AUTHENTICATED** or **REJECTED** → Controller returns HTTP 200 or 401

Each transition is invoked in code synchronously, ensuring a predictable sequence.

---

## Usage

1. **Start the Application**

   ```bash
   mvn spring-boot:run
   ```

   The server will be listening on `http://localhost:8080` by default.

2. **Login Request via Postman**

    * **Method:** POST

    * **URL:** `http://localhost:8080/api/login`

    * **Headers:**

        * `Content-Type: application/json`

    * **Request Body (Valid Credentials):**

      ```json
      {
        "username": "admin",
        "password": "password"
      }
      ```

    * **Expected Response:**

        * **Status:** 200 OK
        * **Body:** `Login successful`

    * **Request Body (Invalid Credentials):**

      ```json
      {
        "username": "admin",
        "password": "wrong"
      }
      ```

    * **Expected Response:**

        * **Status:** 401 Unauthorized
        * **Body:** `Invalid username or password`

---

## Next Steps & Extensions

* Persist users in a database (JPA + MySQL/H2).
* Hash passwords (BCrypt/Argon2).
* Integrate Spring Security for session management or JWT.
* Extend DFA for multi-factor authentication.

---

*Licensed under Catalina.*
