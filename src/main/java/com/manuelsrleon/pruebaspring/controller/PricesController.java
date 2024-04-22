package com.manuelsrleon.pruebaspring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.manuelsrleon.pruebaspring.dto.PriceResponseDto;
import com.manuelsrleon.pruebaspring.model.PriceTimeRange;
import com.manuelsrleon.pruebaspring.service.PricesService;

@RestController
@RequestMapping
public class PricesController {
	@Autowired
	public PricesService pricesService;
	
	@PostMapping("/add")
	public String addDetails(@RequestBody PriceTimeRange personalDetails) {
		pricesService.addDetails(personalDetails);
		return "success";
	}
	
	@GetMapping("/getAll")
	public List<PriceTimeRange> getAll() {
		List<PriceTimeRange> pricesList = null; 
		pricesList = pricesService.getAll();
		return pricesList;
	}
	
	 @GetMapping("/prices")
	    public PriceResponseDto getPriceResponse(
	            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
	            @RequestParam("productId") Long productId,
	            @RequestParam("brandId") Long brandId) {
	        
		 Optional<PriceResponseDto> priceResponseOptional = pricesService.getPriceByDateAndProductAndBrand(applicationDate, productId, brandId); 
		 if(priceResponseOptional.isPresent()){
			 return priceResponseOptional.get();
		 }else {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		 }
	    }
}
