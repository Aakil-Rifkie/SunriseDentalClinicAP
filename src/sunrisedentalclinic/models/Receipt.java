/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.models;

/**
 *
 * @author user
 */
public class Receipt {
    private int receiptID;
    private int appointmentID;
    private String patientName;
    private String dentistName;
    private String treatmentName;
    private double treatmentCost;
    private double consultationFee;
    private double totalAmount;
    private String issueDate;

    public Receipt(int appointmentID, String patientName, String dentistName, String treatmentName, 
            double treatmentCost, double consultationFee) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.dentistName = dentistName;
        this.treatmentName = treatmentName;
        this.treatmentCost = treatmentCost;
        this.consultationFee = consultationFee;
        this.totalAmount = treatmentCost + consultationFee;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDentistName() {
        return dentistName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }  
 }
