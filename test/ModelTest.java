
import org.junit.Test;
import static org.junit.Assert.*;
import sunrisedentalclinic.models.Patient; 
import sunrisedentalclinic.models.Appointment;

public class ModelTest {
    
    @Test
    public void testPatientConstructorAndGetters() {
        Patient patient = new Patient("Kamal Perera", "123 Galle Road", "0771234567");
        assertEquals("Kamal Perera", patient.getName());
        assertEquals("123 Galle Road", patient.getAddress());
        assertEquals("0771234567", patient.getContact());
    }
    @Test
    public void testAppointmentConstructorAndGetters() {
        Appointment appt = new Appointment("Dr. Smith", "Cleaning", "2026-12-01", "10:00");
        assertEquals("Dr. Smith", appt.getDentistName());
        assertEquals("Cleaning", appt.getTreatmentType());
        assertEquals("2026-12-01", appt.getAppointmentDate());
        assertEquals("10:00", appt.getAppointmentTime());
    }
}
