/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.models;

/**
 *
 * @author user
 */
public class Appointment {
    private String dentistName;
    private String treatmentType;
    private String appointmentDate;
    private String appointmentTime;

    public Appointment(String dentistName, String treatmentType, String appointmentDate, String appointmentTime) {
        this.dentistName = dentistName;
        this.treatmentType = treatmentType;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public String getDentistName() {
        return dentistName;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }
}
