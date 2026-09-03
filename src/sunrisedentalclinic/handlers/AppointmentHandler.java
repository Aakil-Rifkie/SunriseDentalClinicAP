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
import java.util.List;
import sunrisedentalclinic.dao.AppointmentDAO;
import sunrisedentalclinic.models.Appointment;
import sunrisedentalclinic.models.Patient;

/**
 *
 * @author user
 */
public class AppointmentHandler implements HttpHandler {

    private AppointmentDAO apptDAO = new AppointmentDAO();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response = "";
        int statusCode = 200;

        try {
            if ("GET".equals(method)) {
                if (path.equals("/dentist")) {
                    List<String> dentists = apptDAO.getAvailableDentists();
                    response = String.join("###", dentists);
                } else if (path.equals("/treatment")) {
                    List<String> treatments = apptDAO.getAvailableTreatment();
                    response = String.join("###", treatments);
                }
            } else if ("POST".equals(method) && path.equals("/registerAppointment")) {
                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes());

                String[] data = requestBody.split("###");

                if (data.length == 7) {
                    Patient newPatient = new Patient(data[0], data[1], data[2]);
                    Appointment newAppt = new Appointment(data[3], data[4], data[5], data[6]);
                    
                    boolean isRegistered = apptDAO.registerPatientAndAppointment(newPatient, newAppt);
                    response = String.valueOf(isRegistered);
                } else {
                    response = "false";
                    statusCode = 400;
                }
            } else {
                statusCode = 404;
            }

        } catch (Exception e) {
            statusCode = 500;
            response = "Error processing request";
        }

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
