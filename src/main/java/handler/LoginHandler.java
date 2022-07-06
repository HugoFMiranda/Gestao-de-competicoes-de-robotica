package handler;

import controller.LoginController;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import model.Authentication;
import model.User;

/**
 * It receives a request from the client, checks if the user is in the database,
 * authenticates the
 * user, and returns a response with the status code 200, 201 or 202
 */
public class LoginHandler {

    // Variables used to store the authentication information.
    private Authentication authProvider;
    boolean isAuthenticated = false;
    private static User user;

    // A constructor.
    public LoginHandler() {
    }

    // A constructor.
    public LoginHandler(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    /**
     * This function returns the authentication provider
     * 
     * @return The authProvider object.
     */
    public Authentication getAuthProvider() {
        return authProvider;
    }

    /**
     * This function sets the authentication provider for the application
     * 
     * @param authProvider The AuthenticationProvider that will be used to
     *                     authenticate the user.
     */
    public void setAuthProvider(Authentication authProvider) {
        this.authProvider = authProvider;
    }

    /**
     * This function returns a boolean value that indicates whether the user is
     * authenticated or not
     * 
     * @return isAuthenticated
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * If the user is authenticated, then set the isAuthenticated variable to true.
     * 
     * @param authenticated This is a boolean value that indicates whether the user
     *                      is authenticated or
     *                      not.
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * This function returns the user
     * 
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * This function sets the user to the user that is passed in
     * 
     * @param user The user object that is being created.
     */
    public void setUser(User user) {
        LoginHandler.user = user;
    }

    /**
     * It receives a request from the client, checks if the user is in the database,
     * authenticates the user, and returns a response with the status code 200, 201
     * or 202
     * 200 - Equipa
     * 201 - Juri
     * 202 - Organizador
     *
     * @param rc RoutingContext
     */
    public void verflogin(RoutingContext rc) {
        String email = rc.request().getParam("emailLogin");
        String password = rc.request().getParam("passwordLogin");
        LoginController lc = new LoginController();
        try {
            int n = lc.verfLogin(email, password);
            HttpServerResponse response = rc.response();
            if (n != 0) {
                response.putHeader("content-type", "application/json; charset=utf-8");
                // * login
                if (n == 1) {
                    authProvider = new Authentication(email, password);
                    isAuthenticated = true;
                    response.setStatusCode(200); // 200 se Equipa
                    user = lc.getEquipa();
                } else if (n == 2) {
                    authProvider = new Authentication(email, password);
                    isAuthenticated = true;
                    response.setStatusCode(201); // 201 se Juri
                    user = lc.getJuri();
                } else if (n == 3) {
                    authProvider = new Authentication(email, password);
                    isAuthenticated = true;
                    response.setStatusCode(202); // 202 se Organizador
                    user = lc.getOrganizador();
                } else {
                    authProvider = new Authentication();
                }
                response.end();
            } else {
                isAuthenticated = false;
                response.setStatusCode(404);
                response.end();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It sets the user to null and isAuthenticated to false
     * 
     * @param rc RoutingContext
     */
    public void sairUsr(RoutingContext rc) {
        HttpServerResponse response = rc.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        user = null;
        isAuthenticated = false;
        response.end();
    }
}
