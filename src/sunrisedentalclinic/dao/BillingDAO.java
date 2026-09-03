/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.dao;
import sunrisedentalclinic.database.DBConnection;
import sunrisedentalclinic.models.Receipt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class BillingDAO {
    public static final double CONSULTATION_FEE = 1500.00;
    
    public Receipt getBillingDetails(int apptId){
        String sql = "SELECT a.appt_id, p.name AS patient_name, a.dentist_name, t.name AS treatment_name, t.cost AS treatment_cost " +
                "FROM appointments a " +
                "INNER JOIN patients p ON a.patient_id = p.patient_id " +
                "INNER JOIN treatments t ON a.treatment_type = t.name " +
                "WHERE a.appt_id = ?";
        
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, apptId);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    return new Receipt(
                    rs.getInt("appt_id"),
                    rs.getString("patient_name"),
                    rs.getString("dentist_name"),
                    rs.getString("treatment_name"),
                    rs.getDouble("treatment_cost"),
                    CONSULTATION_FEE
                    );
                }
            }
        } catch (Exception e){
            System.out.println("Error fetching billing details: " + e.getMessage());
        }
        return null;
    }
    
    public boolean saveReceipt(Receipt receipt){
        String sql = "INSERT INTO receipts (appt_id, total_amount) VALUES (?, ?)";
        boolean isSuccess = false;
        
        try (Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, receipt.getAppointmentID());
            pst.setDouble(2, receipt.getTotalAmount());
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0){
                isSuccess = true;
            }
        } catch (Exception e){
            System.out.println("Error saving receipts" +  e.getMessage());
        }
        return isSuccess;
    }
}
