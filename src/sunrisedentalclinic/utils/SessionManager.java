/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author user
 */
public class SessionManager {

    private static SessionManager instance;
    private Properties properties;
    private static final String FILE_PATH = "session.properties";

    private SessionManager() {
        properties = new Properties();
        loadSession();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    private void loadSession() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                properties.load(fis);
            } catch (Exception e) {
                System.out.println("Could not load session file: " + e.getMessage());
            }
        }
    }

    public void createSession(String username, String role) {
        properties.setProperty("username", username);
        properties.setProperty("role", role);

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            properties.store(fos, "Sunrise Dental Clinic, Active Session");
        } catch (Exception e) {
            System.out.println("Could not save session" + e.getMessage());
        }
    }

    public void clearSession() {
        properties.clear();

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            properties.store(fos, "Session not cleared");
        } catch (Exception e) {
            System.out.println("Could not clear session" + e.getMessage());
        }
    }

    public boolean isLoggedIn() {
        return properties.getProperty("username") != null;
    }

    public String getCurrentUser() {
        return properties.getProperty("username");
    }

    public String getCurrentRole() {
        return properties.getProperty("role");
    }
}
