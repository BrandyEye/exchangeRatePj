package com.guisi.exchangeRate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.guisi.exchangeRate.entities.ExchangeRate;

@Repository
public interface ExchangeRateDao extends JpaRepository<ExchangeRate,Integer> , JpaSpecificationExecutor<ExchangeRate> {

	
}
