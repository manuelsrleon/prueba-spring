package com.manuelsrleon.pruebaspring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelsrleon.pruebaspring.model.PriceTimeRange;

@Repository
public interface PricesRepository extends JpaRepository<PriceTimeRange, Integer>{

	public List<PriceTimeRange> findPrices(LocalDateTime applicationDate, Long productId, Long brandId);
}
