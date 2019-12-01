package com.microservices.currencyconvertservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CurrencyConvertDelegate {

	@Autowired
    RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "callCurrencyManageService_Fallback")
    public String callCurrencyManageService(String from) {
 
        System.out.println("Getting currency manage country details for " + from);
 
        String response = restTemplate
                .exchange("http://localhost:8000/currency-exchange/from/{from}"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {
            }, from).getBody();
 
        System.out.println("Response Received as " + response + " -  " + new Date());
 
        return "NORMAL FLOW !!! - Currency service for country -  " + from + " :::  " +
                    " Currency Manage Service Details " + response + " -  " + new Date();
    }
     
    @SuppressWarnings("unused")
    private String callCurrencyManageService_Fallback(String from) {
 
        System.out.println("Currency Manage Service is down!!! fallback route enabled...");
 
        return "CIRCUIT BREAKER ENABLED!!! No Response From Currency Manage Service at this moment. " +
                    " Service will be back shortly - " + new Date();
    }
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
