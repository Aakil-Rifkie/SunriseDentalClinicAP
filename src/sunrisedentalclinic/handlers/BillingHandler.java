/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.handlers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import sunrisedentalclinic.dao.BillingDAO;
import sunrisedentalclinic.models.Receipt;

/**
 *
 * @author user
 */
public class BillingHandler implements HttpHandler {
    private BillingDAO billingDAO = new BillingDAO();
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response = "";
        int statusCode = 200;
        
        try {
            if ("GET".equals(method) && path.equals("/calculateBill")) {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.startsWith("id=")){
                    int apptId = Integer.parseInt(query.split("=")[1]);
                    Receipt receipt = billingDAO.getBillingDetails(apptId);
                    
                    if (receipt != null){
                        response = String.join("###", 
                                String.valueOf(receipt.getAppointmentID()),
                                receipt.getPatientName(),
                                receipt.getDentistName(),
                                receipt.getTreatmentName(),
                                String.valueOf(receipt.getTreatmentCost()),
                                String.valueOf(receipt.getConsultationFee()),
                                String.valueOf(receipt.getTotalAmount())
                        );
                    } else {
                        statusCode = 404;
                        response = "Billing details not found";
                    }
                } else {
                    statusCode = 400;
                    response = "Missing appointment ID";
                }
            }
            else if ("POST".equals(method) && path.equals("/generateReceipt")){
                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes());
                String[] data = requestBody.split("###");
                
                if (data.length == 2){
                    Receipt receiptToSave = new Receipt(
                    Integer.parseInt(data[0]), "", "", "", 0.0, 0.0
                    );
                    
                    double totalAmount = Double.parseDouble(data[1]);
                    boolean isSaved = billingDAO.saveReceipt(new Receipt(Integer.parseInt(data[0]), "", "", "", totalAmount, 0.0) {
                        @Override
                        public double getTotalAmount(){
                            return totalAmount;
                        }
                    });
                    response = String.valueOf(isSaved);
                } else {
                    statusCode = 400;
                    response = "false";
                }
            } else {
                statusCode = 404;
            }
        } catch (Exception e){
            statusCode = 500;
            response = "Server Error";
        }
        
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
