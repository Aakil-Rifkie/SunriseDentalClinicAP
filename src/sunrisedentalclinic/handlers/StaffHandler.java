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
import sunrisedentalclinic.dao.StaffDAO;
/**
 *
 * @author user
 */
public class StaffHandler implements HttpHandler {

    private StaffDAO staffDAO = new StaffDAO();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if ("POST".equals(exchange.getRequestMethod())) {

            InputStream is = exchange.getRequestBody();
            String requestBody = new String(is.readAllBytes());
            String[] credentials = requestBody.split(",");
            String response = "false"; 
            if (credentials.length == 2) {
                String username = credentials[0];
                String password = credentials[1];

                boolean isValid = staffDAO.authenticateStaff(username, password);
                
                response = String.valueOf(isValid);
            }

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
