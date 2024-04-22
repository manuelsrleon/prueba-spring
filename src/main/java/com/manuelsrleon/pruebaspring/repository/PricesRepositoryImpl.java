package com.manuelsrleon.pruebaspring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.manuelsrleon.pruebaspring.model.PriceTimeRange;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class PricesRepositoryImpl implements PricesRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override	
	public List<PriceTimeRange> findPrices(LocalDateTime applicationDate, Long productId, Long brandId){
		
		return entityManager.createQuery("SELECT p from PriceTimeRange p "
				+ "WHERE p.productId = :productId "
				+ "AND p.brandId = :brandId "
				+ "AND :applicationDate BETWEEN p.startDate AND p.endDate", PriceTimeRange.class)
				.setParameter("productId", productId)
				.setParameter("brandId", brandId)
				.setParameter("applicationDate", applicationDate)
				.getResultList();
	}
}
