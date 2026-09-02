/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sunrisedentalclinicAP;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import sunrisedentalclinic.handlers.StaffHandler;

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
           
           server.setExecutor(null);
           server.start();
           System.out.println("Server is running on 5000");
           
       } 
       catch(IOException e) {
           System.out.println("Error starting the error" + e.getMessage());
       }
    }
}
