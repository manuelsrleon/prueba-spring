package com.manuelsrleon.pruebaspring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.manuelsrleon.pruebaspring.dto.PriceResponseDto;
import com.manuelsrleon.pruebaspring.model.PriceTimeRange;
import com.manuelsrleon.pruebaspring.repository.PricesRepository;

@Service
public interface PricesService {

	public void addDetails(PriceTimeRange priceTimeRange);
	
	public List<PriceTimeRange> getAll();

	public Optional<PriceResponseDto> getPriceByDateAndProductAndBrand(LocalDateTime applicationDate, Long productId,
			Long brandId);
	
}
 