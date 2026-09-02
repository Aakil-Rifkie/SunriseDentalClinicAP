/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.controllers;
/**
 *
 * @author user
 */
public class StaffController {
    private ApiClient apiClient = new ApiClient();
    
    public boolean handleLogin(String username, String password){
        String payload = username + "," + password;
        
        try {
            String response = apiClient.post("/login", payload);
            
            return Boolean.parseBoolean(response.trim());
        }
        catch(Exception e){
            System.out.println("Network or Login Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean handleRegistration(String username, String password, String role){
        String payload = username + "," + password + "," + role;
        
        try {
            String response = apiClient.post("/register", payload);
            return Boolean.parseBoolean(response.trim());
        }
        catch(Exception e) {
            System.out.println("Network or Registration error: " + e.getMessage());
            return false;
        }
    }
}
