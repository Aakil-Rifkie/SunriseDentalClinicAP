/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunrisedentalclinic.controllers;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URL;
import java.time.Duration;

public class ApiClient {

    private final String baseUrl = "http://localhost:5000";
    private final HttpClient httpClient;

    public ApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public String post(String endpoint, String data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "text/plain")
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String get(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
                
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200){
            throw new RuntimeException("HTTP GET Failed with code:  " + response.statusCode());
        }
        return response.body();
    }
}
