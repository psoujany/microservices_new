package com.microservices.currencyconversionmanageservice;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CurrencyConversionManageController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getConversionFactor(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangevalue = repository.findByFromAndTo(from, to);
		exchangevalue.setPort(Integer.parseInt((environment.getProperty("local.server.port"))));
		return exchangevalue;
		
	}
	
	@PostMapping("/currency-exchange")
	public ExchangeValue addConversionFactor(@RequestBody ExchangeValue exchangevalue) {
		repository.save(exchangevalue);	
		return exchangevalue;
		
	}
	
	@PostMapping("/currency-exchage-update")
	public ExchangeValue updateConversionFactor(@RequestBody ExchangeValue exchangevalue) {
		repository.save(exchangevalue);	
		return exchangevalue;
	}
	
	@GetMapping("/test")
	@HystrixCommand(fallbackMethod = "myFallbackMethod")
	public @ResponseBody String test() {
		if (new Random().nextBoolean()) {
			return "Everything Working Fine";
		} else {
			throw new RuntimeException();
		}
	}
	
	public String myFallbackMethod() {
		return "Fallback Enabled. Normal flow selected !!";
	}
}
