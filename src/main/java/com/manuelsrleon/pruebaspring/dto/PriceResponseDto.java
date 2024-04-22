package com.manuelsrleon.pruebaspring.dto;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PriceResponseDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private Long brandId;
	private LocalDateTime applicationDate;
	private Long priceList;
	private Float price;
	//Not including the currency as it is not specified. It wouldn't hurt to put it here, though.
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public PriceResponseDto(Long productId, Long brandId, Long priceList, LocalDateTime applicationDate, Float price) {
		super();
		this.productId = productId;
		this.brandId = brandId;
		this.priceList = priceList;
		this.applicationDate = applicationDate;
		this.price = price;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Long getPriceList() {
		return priceList;
	}
	public void setPriceList(Long priceList) {
		this.priceList = priceList;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
}
