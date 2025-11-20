package com.myeatclub.eatClubApp.controller;


import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

public class TwillioDemo {



    public static void main(String[] args) {

        System.out.println("Hello World!");


        String ACCOUNT_SID = "AC669677d00f3b54045e7bbb2e1ebed88a";
        String AUTH_TOKEN = "abac975cf420c0533796861398b4287c";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new com.twilio.type.PhoneNumber("+918839685816"),
                        new com.twilio.type.PhoneNumber("+12525883727"),
                        "App is live now!")
                .create();

        System.out.println(message.getBody());
    }

}

