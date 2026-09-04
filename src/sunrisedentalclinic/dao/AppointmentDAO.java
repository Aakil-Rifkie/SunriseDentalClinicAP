/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.dao;

import sunrisedentalclinic.database.DBConnection;
import sunrisedentalclinic.models.Appointment;
import sunrisedentalclinic.models.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AppointmentDAO {

    public List<String> getAvailableDentists() {
        List<String> dentists = new ArrayList<>();
        String sql = "SELECT username FROM staff WHERE role = 'Dentist'";

        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); 
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                dentists.add(rs.getString("username"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching dentists: " + e.getMessage());
        }
        return dentists;
    }

    public List<String> getAvailableTreatment() {

        List<String> treatments = new ArrayList<>();
        String sql = "SELECT name FROM treatments";

        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                treatments.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching treatments: " + e.getMessage());
        }
        return treatments;
    }

    public boolean checkAvailability(String dentist, String date, String time) {
        String sql = "SELECT COUNT(*) FROM appointments WHERE dentist_name =? AND appt_date = ? AND appt_time = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, dentist);
            pst.setString(2, date);
            pst.setString(3, time);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error checking availability " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean registerPatientAndAppointment(Patient patient, Appointment appt) {

        if (!checkAvailability(appt.getDentistName(), appt.getAppointmentDate(), appt.getAppointmentTime())) {
            System.out.println("Double booking prevented! This time slot is already taken");
            return false;
        }
        
        String checkPatient = "SELECT patient_id FROM patients WHERE contact = ?";
        String insertPatient = "INSERT INTO patients (name, address, contact) VALUES (?, ?, ?)";
        String insertAppt = "INSERT INTO appointments (patient_id, dentist_name, treatment_type, appt_date, appt_time) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection()){
           con.setAutoCommit(false);
           int patientId = -1;
           
           try (PreparedStatement pstCheck = con.prepareStatement(checkPatient)){
               pstCheck.setString(1, patient.getContact());
               try(ResultSet rsCheck = pstCheck.executeQuery()){
                   if (rsCheck.next()){
                       patientId = rsCheck.getInt("patient_id");
                   }
               }
           }
                   
           if (patientId == -1){
               try (PreparedStatement pstPatient = con.prepareStatement(insertPatient, Statement.RETURN_GENERATED_KEYS)){
                pstPatient.setString(1, patient.getName());
                pstPatient.setString(2, patient.getAddress());
                pstPatient.setString(3, patient.getContact());
                pstPatient.executeUpdate();
                
                    try(ResultSet rsKeys = pstPatient.getGeneratedKeys()){
                        if (rsKeys.next()){
                            patientId = rsKeys.getInt(1);
                        }
                    }
               }
           }
           
           if (patientId != -1){
               try(PreparedStatement pstAppt = con.prepareStatement(insertAppt)){
                   pstAppt.setInt(1, patientId);
                   pstAppt.setString(2, appt.getDentistName());
                   pstAppt.setString(3, appt.getTreatmentType());
                   pstAppt.setString(4, appt.getAppointmentDate());
                   pstAppt.setString(5, appt.getAppointmentTime());
                   pstAppt.executeUpdate();
               }
               con.commit();
               return true;
           } else {
               con.rollback();
               return false;
           }  
        } catch (Exception e){
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }
    
    public String searchAppointment(int apptId){
        String sql = "SELECT a.appt_Id, p.name, p.address, p.contact, a.dentist_name, a.treatment_type, a.appt_date, a.appt_time " +
                "FROM appointments a " +
                "INNER JOIN patients p ON a.patient_id = p.patient_id " +
                "WHERE a.appt_id = ?";
        
        try (Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, apptId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()){
                    return String.join("###",
                            String.valueOf(rs.getInt("appt_Id")),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("contact"),
                            rs.getString("dentist_name"),
                            rs.getString("treatment_type"),
                            rs.getString("appt_date"),
                            rs.getString("appt_time")
                        );
                }
            }
        } catch (Exception e){
            System.out.println("Error searching appointments: " + e.getMessage());
        } return null;
    }
}


