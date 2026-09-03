/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.controllers;
import sunrisedentalclinic.models.Receipt;

/**
 *
 * @author user
 */
public class BillingController {
    private ApiClient apiClient = new ApiClient();
    
    public Receipt calculateTotal(String apptIdStr){
        try{
            int id = Integer.parseInt(apptIdStr);
            String response = apiClient.get("/calculateBill?id=" + id);
            
            if (!response.isEmpty() && response.contains("###")){
                String[] data = response.split("###");
                if (data.length == 7){
                    return new Receipt(
                    Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            Double.parseDouble(data[4]),
                            Double.parseDouble(data[5])
                    );
                }
            }
        } catch (Exception e){
            System.out.println("Error calculating total: " + e.getMessage());
        }
        return null;
    }
    
    public boolean confirmPayment(Receipt receipt){
        String payload = receipt.getAppointmentID() + "###" + receipt.getTotalAmount();
        try {
            String response = apiClient.post("/generateReceipt", payload);
            return Boolean.parseBoolean(response.trim());
        } catch (Exception e){
            System.out.println("Error confirming payment" + e.getMessage());
            return false;
        } 
    }
}
