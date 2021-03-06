package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String passwordHash;

    public User() {
    }

    /**
     * Constructor creates a new user with username and hashed password
     * @param username
     * @param password
     */
    public User(String username, String password) {
        if (username == null || password == null) {
            throw new NullPointerException("Null arguments invalid");
        } else if (username.trim().equals("") || password.trim().equals("")) {
            throw new InvalidArgumentException("Cannot use empty strings to create account");
        } else {
            this.username = username;
            this.passwordHash = secureHash(password);
        }
    }

    /**
     * Generates a salted password hash for secure password storage
     * USES Java Bcrypt algorithm
     * @param password plaintext password to be hashed
     * @return null if password is null, hashed password otherwise
     */
    protected static String secureHash(String password) {
        if (password == null || password.equals("")) {
            throw new InvalidArgumentException("Cannot hash invalid string");
        } else {
            String salt = BCrypt.gensalt();
            return BCrypt.hashpw(password, salt);
        }
    }

    /**
     * Checks password against a users stored hash
     * @param passwordToCheck
     * @return true if the passwordToCheck matches passwordHash
     */
    public boolean authenticate(String passwordToCheck) {
        if (passwordToCheck == null || passwordToCheck.equals("")) {
            throw new InvalidArgumentException("Cannot authenticate invalid strings");
        } else {
            boolean matched = BCrypt.checkpw(passwordToCheck, this.passwordHash);
            return matched;
        }
    }

    /**************************** GETTERS AND SETTERS     ****************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
