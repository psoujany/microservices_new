package com.microservices.currencyconversionmanageservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping(value="/currency-exchange-add")
	public ResponseEntity<Object> addConversionFactor(@RequestBody ExchangeValue exchangevalue) {
	    	repository.save(exchangevalue);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/")
					.buildAndExpand().toUri();

					return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/currency-exchage-update")
	public ExchangeValue updateConversionFactor(@RequestBody ExchangeValue exchangevalue) {
		repository.save(exchangevalue);
		return exchangevalue;
	}
	
	@GetMapping("/currency-exchange-all")
	public List<ExchangeValue> findall(){
	return repository.findAll();
	} 
}
