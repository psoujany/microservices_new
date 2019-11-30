package com.microservices.currencyconvertservice;

import java.math.BigDecimal;

public class CurrencyConvertBean {

	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalAmount;
	private int port;
	
	public CurrencyConvertBean() {
		
	}
	
	public CurrencyConvertBean(Long id,String from, String to, BigDecimal quantity, BigDecimal conversionMultiple, BigDecimal totalAmount, int port) {
		super();
		this.id=id;
		this.from=from;
		this.to=to;
		this.quantity=quantity;
		this.conversionMultiple=conversionMultiple;
		this.totalAmount=totalAmount;
		this.port=port;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
