package com.myeatclub.eatClubApp.controller;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.springframework.web.bind.annotation.*;





@RestController
@CrossOrigin("http://localhost:3000")
public class ApiFetcher {

    @GetMapping("/fetch-api-data")
    public String fetchApiData() throws InterruptedException {

        System.out.println("Fetching API data...");
        boolean mybool = checkRestaurantStatus("https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=46&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub");

        return mybool ? "API data fetched successfully!" : "Somthing went wrong!";
    }

    public static void testing(){
        System.out.println("hello from testing");
    }



   public static boolean checkRestaurantStatus(String url) throws InterruptedException {
       boolean isOverloaded = false;
       do{
           try {
               HttpClient client = HttpClient.newHttpClient();
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .GET()
                       .build();

               HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

               String body = response.body();
               //System.out.println("API Response: " + body);

               // Check if API response contains "restaurant overload"
               if (body.toLowerCase().contains("overload")) {
                   System.out.println("⚠️ Restaurant is overloaded. Please try again later.");
               } else {
                   System.out.println("✅ Restaurant is accepting orders.");
                   isOverloaded = true;
               }

           } catch (Exception e) {
               // e.printStackTrace();
               System.out.println("❌ Error occurred while checking restaurant status: ");;
           }

           Thread.sleep(5000);


       }while(!isOverloaded);

      // System.out.println("Finished checking restaurant status.");

       return true;
   }

    public static void main(String[] args) {

     //   for testing purpose

         String url = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=46&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub";
        //boolean isOverloaded = false;
         try {
            
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String body = response.body();
                //System.out.println("API Response: " + body);

                // Check if API response contains "restaurant overload"
                if (body.toLowerCase().contains("overload")) {
                    System.out.println("⚠️ Restaurant is overloaded. Please try again later.");
                } else {
                    System.out.println("✅ Restaurant is accepting orders.");
                   // isOverloaded = true;
                }

            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("❌ Error occurred while checking restaurant status: ");;
            }
    }
}



