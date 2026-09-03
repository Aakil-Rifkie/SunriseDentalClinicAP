package sunrisedentalclinic.dao;

import sunrisedentalclinic.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffDAO {

    public String authenticateStaff(String username, String password) {
        String role = null;
        String sql = "SELECT role FROM staff WHERE username = ? AND password = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password); 
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                role = rs.getString("role"); 
            }
        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
        }
        return role;
    }

    public boolean registerStaff(String username, String password, String role) {
     
        if (checkUserExists(username)) {
            System.out.println("Username already taken!");
            return false; 
        }

        String sql = "INSERT INTO staff (username, password, role) VALUES (?, ?, ?)";
        boolean isSuccess = false;
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true; 
            }
        } catch (Exception e) {
            System.out.println("Registration Error: " + e.getMessage());
        }
        return isSuccess;
    }
    
    private boolean checkUserExists(String username) {
        boolean exists = false;
        String sql = "SELECT username FROM staff WHERE username = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                exists = true; 
            }
        } catch (Exception e) {
            System.out.println("Check User Error: " + e.getMessage());
        }
        return exists;
    }
}