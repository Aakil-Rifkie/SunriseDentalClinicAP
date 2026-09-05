
import org.junit.Test;
import static org.junit.Assert.*;
import sunrisedentalclinic.models.Receipt;

public class ReceiptTest {
    @Test
    public void testTotalAmountCalculation() {
        Receipt receipt = new Receipt(1, "John", "Dr. Smith", "Cleaning", 3000.00, 1500.00);
        assertEquals("Total should be treatmentCost + consultationFee",
                4500.00, receipt.getTotalAmount(), 0.01);
    }
    @Test
    public void testGettersReturnCorrectValues() {
        Receipt receipt = new Receipt(5, "Jane", "Dr. Perera", "Filling", 5000.00, 1500.00);
        assertEquals(5, receipt.getAppointmentID());
        assertEquals("Jane", receipt.getPatientName());
        assertEquals("Dr. Perera", receipt.getDentistName());
        assertEquals("Filling", receipt.getTreatmentName());
        assertEquals(5000.00, receipt.getTreatmentCost(), 0.01);
        assertEquals(1500.00, receipt.getConsultationFee(), 0.01);
    }
    @Test
    public void testSetReceiptIDAndIssueDate() {
        Receipt receipt = new Receipt(1, "John", "Dr. Smith", "Cleaning", 3000.00, 1500.00);
        receipt.setReceiptID(100);
        receipt.setIssueDate("2026-09-04");
        assertEquals(100, receipt.getReceiptID());
        assertEquals("2026-09-04", receipt.getIssueDate());
    }
    @Test
    public void testZeroCostReceipt() {
        Receipt receipt = new Receipt(1, "Test", "Dr. Test", "Checkup", 0.00, 1500.00);
        assertEquals("Zero treatment cost should still add consultation fee",
                1500.00, receipt.getTotalAmount(), 0.01);
    }
}
