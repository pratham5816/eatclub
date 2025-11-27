package com.myeatclub.eatClubApp.service;

import com.myeatclub.eatClubApp.entity.UserData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EatClubApiTester {


    public static boolean testApi(UserData userData){
        String url = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=" + userData.getOutletName()+ "&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub";
       
        System.out.println("In testApi method..." + "ouletIDname"+userData.getOutletName());
        
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // String body = response.body();
            int status = response.statusCode();
            //System.out.println("API Response: " + body);

            // Check if API wokring or not!
            if (status >= 200 && status < 300) {
                System.out.println("In testApi method... inside");
                return true;
            } else {
                return false;
            }
            
        }catch (Exception e) {
            // e.printStackTrace();
            // System.out.println("❌ Error occurred while checking restaurant status: ");
            return false;
        }
        
        



    }


    public static boolean checkUntilQueueIsClear(UserData userData) throws InterruptedException {

        System.out.println("Checking until queue is clear...");
        System.out.println(userData);
        // boolean isOverloaded = false;
        String url = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=" + userData.getOutletName().trim()+ "&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub";


        while (true) {

            System.out.println("In loop of queue clear");
            try {
                  System.out.println("In try of queue clear");
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body().toLowerCase();

                if (!body.toLowerCase().contains("overloaded")) {
                     System.out.println("In overloaded of queue clear");
                    return true;  // restaurant OK
                }

            } catch (Exception e) {
                return false;   // API failed
            }

            Thread.sleep(5000);  // wait before retry
        }

}

//--------------------------------------
//         do{
//             try {
//                 HttpClient client = HttpClient.newHttpClient();
//                 HttpRequest request = HttpRequest.newBuilder()
//                         .uri(URI.create(url))
//                         .GET()
//                         .build();

//                 HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//                 String body = response.body();

//                 //System.out.println("API Response: " + body);

//                 // Check if API response contains "restaurant overload"
// //                if (body.toLowerCase().contains("overload")) {
// //                    System.out.println("⚠️ Restaurant is overloaded. Please try again later.");
// //                } else {
// //                    System.out.println("✅ Restaurant is accepting orders.");
// //                    isOverloaded = true;
// //                }
//                 if (!body.toLowerCase().contains("overloaded")){ // if overloaded found in API means = restaurant overloaded 
//                     isOverloaded = true;
//                     break;
//                 }

//             } catch (Exception e) {
//                 // e.printStackTrace();
//                 break;
//             }

//             Thread.sleep(5000);  // so that api hits will not be very fast // call bombing should not take place

//         }while(true);



    //     return isOverloaded ? true : false; // in-case  somthing went wrong (error while checking overload)

    // }

}
