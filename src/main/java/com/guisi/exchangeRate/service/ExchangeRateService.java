package com.guisi.exchangeRate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guisi.exchangeRate.entities.ExchangeRate;
import com.guisi.exchangeRate.vo.SearchBean;

@Service
public interface ExchangeRateService {
	
	
	/**
	 * api查匯率資料
	 */
	public void takeDailyForeignExchangeRatesAPI();
	
	/**
	 * @param entity
	 */
	public void updateExRate(ExchangeRate entity);
	
	/**
	 * @return
	 */
	public ExchangeRate addExRate();
	
	/**
	 * @param id
	 */
	public void deleteExRate(Integer id);
	
	/**
	 * @return
	 */
	public List<ExchangeRate> findAllExRate(SearchBean sb);
	
	/**
	 * @return
	 */
	public List<ExchangeRate> findAllExRate();
	
	/**
	 * @param id
	 * @return
	 */
	public ExchangeRate findById(Integer id);

}
