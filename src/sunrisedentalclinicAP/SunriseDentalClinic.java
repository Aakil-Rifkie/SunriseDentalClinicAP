/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sunrisedentalclinicAP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author user
 */
public class SunriseDentalClinic {

   
    public static void main(String[] args) {
       try {
           HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
           server.createContext("/", new MyHandler());
           server.setExecutor(null);
           server.start();
           
           System.out.println("Server is running port 5000");
       } 
       catch(IOException e) {
           System.out.println("Error starting the erorr" + e.getMessage());
       }
    }
    
    static class MyHandler implements HttpHandler{
        @Override 
        public void handle(HttpExchange exchange) throws IOException
        {
            String response = "Hello this is a simple HTTP server";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
