/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.utils;

/**
 *
 * @author user
 */
public class SessionManager {

    private static SessionManager instance;
    private String username;
    private String role;

    private static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void createSession(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public void clearSession() {
        this.username = null;
        this.role = null;
    }

    public boolean isLoggedin() {
        return username != null;
    }

    public String getCurrentUser() {
        return username;
    }

    public String getCurrentRole() {
        return role;
    }
}
