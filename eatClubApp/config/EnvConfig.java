package com.myeatclub.eatClubApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Value("${custom.mysid:default-sid-value}")
    private String customMySid;
     // we are getting values injected by @Value annotations.   

    @Value("${custom.mypassword}")
    private String customMypassword;

    @Value("${custom.twilliophone}")
    private String customTwillioPhone;

    public String getTwillioPhone(){return  customTwillioPhone;}

    public String getCustomMyPassword(){return customMypassword;}

    public String getCustomMySid() {return customMySid;}
}
