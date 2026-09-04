/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sunrisedentalclinicAP;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import sunrisedentalclinic.handlers.StaffHandler;
import sunrisedentalclinic.handlers.AppointmentHandler;
import sunrisedentalclinic.handlers.BillingHandler;
/**
 *
 * @author user
 */
public class SunriseDentalClinic {
   
    public static void main(String[] args) {
       try {
           HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
           
           StaffHandler staffHandler = new StaffHandler();
           server.createContext("/login", staffHandler);
           server.createContext("/register", staffHandler); 
           
           AppointmentHandler apptHandler = new AppointmentHandler();
           server.createContext("/dentist", apptHandler);
           server.createContext("/treatment", apptHandler);
           server.createContext("/checkAvailability", apptHandler);
           server.createContext("/registerAppointment", apptHandler);    
           server.createContext("/searchAppointment", apptHandler);
           
           BillingHandler billHandler = new BillingHandler();
           server.createContext("/calculateBill", billHandler);
           server.createContext("/generateReceipt", billHandler);
           
           server.setExecutor(null);
           server.start();
           System.out.println("Server is running on 5000");
           
           sunrisedentalclinic.views.Login loginScreen = new sunrisedentalclinic.views.Login();
           loginScreen.setVisible(true);
           
       } 
       catch(IOException e) {
           System.out.println("Error starting the error" + e.getMessage());
       }
    }
}
