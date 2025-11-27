package com.myeatclub.eatClubApp.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.myeatclub.eatClubApp.entity.UserData;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import jakarta.annotation.PostConstruct;

// @Component
public class Domo01 {

    //   @Value("${my.api.token}")
    //     private String apiToken;
    
    //     // @Value("${myapp.mykey}")
    //     // private String mykey;

    //     @Value("${server.port}")
    //     private String port;

    // @PostConstruct
    // public void init() {
    //     System.out.println("api: " + apiToken);
    //     //  System.out.println("api from env which exported by terminal" + mykey);
    //     System.out.println(port);
    // }

    public static void main(String[] args) throws Exception{

        System.out.println(checkUntilQueueIsClear(new UserData("pune" , "8839685816" , "46")));
        
    }

    
    public static boolean checkUntilQueueIsClear(UserData userData) throws InterruptedException {

        System.out.println("Checking until queue is clear...");

        // boolean isOverloaded = false;
        String url = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=" + userData.getOutletName().trim()+ "&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub";


        while (true) {

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body().toLowerCase();

                if (!body.toLowerCase().contains("overloaded")) {
                    return true;  // restaurant OK
                }

            } catch (Exception e) {
                return false;   // API failed
            }

            Thread.sleep(5000);  // wait before retry
        }

}

}
