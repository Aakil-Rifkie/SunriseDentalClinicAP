/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.controllers;
import sunrisedentalclinic.models.Appointment;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import sunrisedentalclinic.models.Patient;

/**
 *
 * @author user
 */
public class AppointmentController {
    private ApiClient apiClient = new ApiClient();
    
    public List<String> loadAvailableDentists(){
        try {
            String response = apiClient.get("/dentist");
            if(!response.isEmpty()){
                return Arrays.asList(response.split("###"));
            }
        } catch (Exception e){
            System.out.println("Failed to load dentsits: " + e.getMessage());
        }
        
        return new ArrayList<>();
    }
    
    public List<String> loadAvailableTreatments(){
        try {
            String response = apiClient.get("/treatment");
            if (!response.isEmpty()){
                return Arrays.asList(response.split("###"));
            }
        } catch (Exception e){
            System.out.println("Failed to load treatments: " + e.getMessage());
        }
        
        return new ArrayList<>();
    }
    
    public boolean isSlotAvailable(String dentist, String date, String time){
        String payload = String.join("###", dentist, date, time);
        
        try{
            String response = apiClient.post("/checkAvailability", payload);
            return Boolean.parseBoolean(response.trim());
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean processRegistration(Patient patient, Appointment appt){
        String payload = String.join("###", 
                patient.getName(),
                patient.getAddress(),
                patient.getContact(),
                appt.getDentistName(),
                appt.getTreatmentType(),
                appt.getAppointmentDate(),
                appt.getAppointmentTime());
        
        try{
            String response = apiClient.post("/registerAppointment", payload);
            return Boolean.parseBoolean(response.trim());
        } catch (Exception e){
            System.out.println("Registration Network Error " + e.getMessage());
            return false;
        }
    }
    
    public String getAppointmentDetails(String apptIdStr){
        try {
            int id = Integer.parseInt(apptIdStr);
            return apiClient.get("/searchAppointment?id=" + id);
        } catch(NumberFormatException e) {
            return "Error: Appointment ID must be a valid number";
        } catch (RuntimeException e){
            if (e.getMessage().contains("404")){
                return "Error: No appointment found with that ID";
            }
            return "Error: Server Connection Failed";
        } catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
