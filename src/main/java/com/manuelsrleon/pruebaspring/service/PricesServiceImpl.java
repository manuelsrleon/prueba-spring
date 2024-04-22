package com.manuelsrleon.pruebaspring.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelsrleon.pruebaspring.dto.PriceResponseDto;
import com.manuelsrleon.pruebaspring.model.PriceTimeRange;
import com.manuelsrleon.pruebaspring.repository.PricesRepository;

@Service
public class PricesServiceImpl implements PricesService{
	@Autowired
    private PricesRepository pricesRepository;
	private ResponseMapper responseMapper = new ResponseMapper();
	
	@Override
	public void addDetails(PriceTimeRange priceTimeRange)  {
		pricesRepository.save(priceTimeRange);
	}
	
	@Override
	public List<PriceTimeRange> getAll() 	
	 {	
		List<PriceTimeRange> pricesList = null;
		pricesList = pricesRepository.findAll();	
		return pricesList;
	}


	@Override
	public Optional<PriceResponseDto> getPriceByDateAndProductAndBrand(LocalDateTime applicationDate, Long productId,
			Long brandId) {
		
		return pricesRepository.findPrices(applicationDate, productId, brandId)
                .stream()
                .max(Comparator.comparingLong(PriceTimeRange::getPriority))
                .map(priceTimeRange -> responseMapper.toDto(priceTimeRange, applicationDate));
	}
	
}