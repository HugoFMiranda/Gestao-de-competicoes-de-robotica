package model;

/**
 * Authentication is a class that has a username and password.
 */
public class Authentication {
    private String username;
    private String password;

    // A constructor.
    public Authentication(){

    }

    // A constructor.
    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This function returns the username of the user
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This function returns the password.
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

}
