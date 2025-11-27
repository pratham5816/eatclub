package com.myeatclub.eatClubApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myeatclub.eatClubApp.config.EnvConfig;
import com.myeatclub.eatClubApp.entity.UserData;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;



@Service
public class TwillioService
{   

    @Autowired
    private EnvConfig envConfig;


    String ACCOUNT_SID;
    String AUTH_TOKEN;
    String TWILLIO_PHONE;

    @PostConstruct
    public void getEnvCredentails(){
        ACCOUNT_SID = envConfig.getCustomMySid();
        AUTH_TOKEN  = envConfig.getCustomMyPassword();
        TWILLIO_PHONE = envConfig.getTwillioPhone();
    }

   

    public void sendTwillioMessage(UserData userData) throws InterruptedException {
        if (EatClubApiTester.testApi(userData)) {
            // if API is working fine the go ahead and check until queue is clear
            // System.out.println("api is working fine, sending twillio message...")
            if (EatClubApiTester.checkUntilQueueIsClear(userData) == true) {
                sendSMS(userData);
            } else {
                sendDownSMS(userData);
            }
        }
    }

    public void sendSMS(UserData myData){

        // String ACCOUNT_SID = "AC669xxxxebed88a"; // used for testing!
        // String AUTH_TOKEN = "abxx98b4287c";


        System.out.println("In send SMS");
        System.out.println("AC SID " + ACCOUNT_SID);
        System.out.println("AUTH TOCKEN" + AUTH_TOKEN);
        System.out.println("PHONE" + TWILLIO_PHONE);
        try{
            
            Twilio.init(ACCOUNT_SID.trim(), AUTH_TOKEN.trim());

            Message message = Message
                .creator(new com.twilio.type.PhoneNumber("+91"+myData.getPhone().trim()),
                        new com.twilio.type.PhoneNumber(TWILLIO_PHONE),
                        "App is live now!")
                .create();

             System.out.println(message.getBody());
        }catch(Exception e){
            System.out.println("ERROR");
        }
       
    }

    public void sendDownSMS(UserData myData){
        // String ACCOUNT_SID = "ACxxxxxxxxx";
        // String AUTH_TOKEN = "axxxxxxx7c";

        System.out.println("In send downSMS");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new com.twilio.type.PhoneNumber("+91" + myData.getPhone().trim()),
                        new com.twilio.type.PhoneNumber(TWILLIO_PHONE),
                        "App is down!")
                .create();

        System.out.println(message.getBody());
    }



}
