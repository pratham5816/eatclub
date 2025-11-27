package com.myeatclub.eatClubApp.service;

import com.twilio.Twilio;

// import io.github.cdimascio.dotenv.Dotenv;


// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


// import java.net.URI;

public class Demo {
    

    public static void main(String[] args) {



        Twilio.init("AC66XXXX88a", "d4XXXXXX95");

        Message message = Message
        .creator(new com.twilio.type.PhoneNumber("+91XXXXX85816"),
                new com.twilio.type.PhoneNumber("+12XXXXXXXX7"),
                "App is live now!")
        .create();

        System.out.println(message.getBody());
            

        // // System.out.println("number" + System.getenv("TWILIO_PHONE_NUMBER"));
        //  Dotenv dotenv = Dotenv.configure()
        //         .directory("./")  // Project root
        //         .load();

        // // Now you can access environment variables
        // String phoneNumber = dotenv.get("TWILIO_PHONE_NUMBER");
        // String accountSid = dotenv.get("TWILIO_ACCOUNT_SID");
        // String authToken = dotenv.get("TWILIO_AUTH_TOKEN");

        // System.out.println("\n=== Environment Variables ===");
        // System.out.println("Phone Number: " + (phoneNumber != null ? phoneNumber : "NOT FOUND"));
        // System.out.println("Account SID: " + (accountSid != null ? accountSid : "NOT FOUND"));
        // System.out.println("Auth Token: " + (authToken != null ? authToken : "NOT FOUND"));
        // System.out.println("============================\n");
        // String o = "46";

        // String url = "https://api.box8.in/catalog/v2/outlets/app_brand?lat=18.6010921&lon=73.7641245&ver=51&platform=web&brand_id=15&outlet_id=" + o+ "&service_type=DEL101&app_id=19&desktop_view=1&origin=eatClub";


        //  try { 
            
        //         HttpClient client = HttpClient.newHttpClient();
        //         HttpRequest request = HttpRequest.newBuilder()
        //                 .uri(URI.create(url))
        //                 .GET()
        //                 .build();

        //         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //         String body = response.body();
        //         //System.out.println("API Response: " + body);

        //         // Check if API response contains "restaurant overload"
        //         if (body.toLowerCase().contains("overload")) {
        //             System.out.println("⚠️ Restaurant is overloaded. Please try again later.");
        //         } else {
        //             System.out.println("✅ Restaurant is accepting orders.");
                    
        //         }

        //     } catch (Exception e) {
        //         // e.printStackTrace();
        //         System.out.println("❌ Error occurred while checking restaurant status: ");;
        //     }

    }
}
