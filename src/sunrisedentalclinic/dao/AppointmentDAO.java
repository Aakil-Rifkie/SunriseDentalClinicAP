/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.dao;

import sunrisedentalclinic.database.DBConnection;
import sunrisedentalclinic.models.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class AppointmentDAO {

    public List<String> getAvailableDentists() {
        List<String> dentists = new ArrayList<>();
        String sql = "SELECT name FROM dentists";

        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                dentists.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching dentists: " + e.getMessage());
        }
        return dentists;
    }

    public List<String> getAvailableTreatment() {

        List<String> treatments = new ArrayList<>();
        String sql = "SELECT name FROM treatments";

        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                treatments.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching treatments: " + e.getMessage());
        }
        return treatments;
    }

    public boolean registerAppointment(Appointment appt) {
        String sql = "INSERT INTO appointments (patient_name, address, contact, dentist_name, treatment_type, appt_date, appt_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean isSuccess = false;

        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, appt.getPatientName());
            pst.setString(2, appt.getAddress());
            pst.setString(3, appt.getContactNumber());
            pst.setString(4, appt.getDentistName());
            pst.setString(5, appt.getTreatmentType());
            pst.setString(6, appt.getAppointmentDate());
            pst.setString(7, appt.getAppointmentTime());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error registering appointment: " + e.getMessage());
        }
        return isSuccess;
    }
}

