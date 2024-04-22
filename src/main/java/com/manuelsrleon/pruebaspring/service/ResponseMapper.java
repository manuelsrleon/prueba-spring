package com.manuelsrleon.pruebaspring.service;

import java.time.LocalDateTime;

import com.manuelsrleon.pruebaspring.dto.PriceResponseDto;
import com.manuelsrleon.pruebaspring.model.PriceTimeRange;

public class ResponseMapper {
	public PriceResponseDto toDto(PriceTimeRange priceTimeRange, LocalDateTime applicationDate) {
		return new PriceResponseDto(priceTimeRange.getProductId(), priceTimeRange.getBrandId(), priceTimeRange.getPriceList(), applicationDate, priceTimeRange.getPrice());
	}
}
