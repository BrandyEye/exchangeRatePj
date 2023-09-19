package com.guisi.exchangeRate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guisi.exchangeRate.entities.ExchangeRate;

@Service
public interface ExchangeRateService {
	
	
	public void takeDailyForeignExchangeRatesAPI();
	
	public void updateExRate(ExchangeRate entity);
	
	public ExchangeRate addExRate();
	
	public void deleteExRate(Integer id);
	
	public List<ExchangeRate> findAllExRate();
	
	public ExchangeRate findById(Integer id);

}
