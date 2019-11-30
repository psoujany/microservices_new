package com.microservices.currencyconvertservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-conversion-manage-service", url="localhost:8000")
@FeignClient(name="currency-conversion-manage-service")
@RibbonClient(name="currency-conversion-manage-service")
public interface CurrencyManageServiceProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConvertBean getConversionFactor(@PathVariable String from, @PathVariable String to);
}
