package com.guisi.exchangeRate.serviceImp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guisi.exchangeRate.dao.ExchangeRateDao;
import com.guisi.exchangeRate.entities.ExchangeRate;
import com.guisi.exchangeRate.handler.ExchangeRateHandler;
import com.guisi.exchangeRate.service.ExchangeRateService;
import com.guisi.exchangeRate.vo.SearchBean;

@Service
public class ExchangeRateServiceImp implements ExchangeRateService {
	
	private ExchangeRateDao dao;

	public ExchangeRateServiceImp(ExchangeRateDao dao) {
		super();
		this.dao = dao;
	}
	@Transactional 
	@Cacheable(value = "EXCache")
	@Override
	public void takeDailyForeignExchangeRatesAPI() {
		String dataJson = ExchangeRateHandler.dailyForeignExchangeRates_API();
			List<ExchangeRate> exchangeList = jsonConvert(dataJson);
		dao.saveAll(exchangeList);
	}

	@Transactional 
	@Override
	public void updateExRate(ExchangeRate entity) {
		dao.save(entity);
	}
	@Transactional 
	@Override
	public void deleteExRate(Integer id) {
		dao.deleteById(id);
	}
	@Override
	public List<ExchangeRate> findAllExRate(SearchBean sb) {
		if(sb == null) {
			sb = new SearchBean(20,1,"");
		}
		int num = sb.getPageNum()!=null?sb.getPageNum()-1:0;
		int size = sb.getPageSize()!=null?sb.getPageSize():20;
		String date = sb.getDateCondition()!=null?sb.getDateCondition():"";
		Pageable pg = PageRequest.of(num, size,Sort.Direction.ASC, "sn");
		return dao.findAll(searchConditionBuilder(date),pg).getContent();
	}
	
	@Override
	public List<ExchangeRate> findAllExRate() {
		return findAllExRate(null);
	}
	
	@Override
	public ExchangeRate findById(Integer id) {
		return dao.findById(id).get();
	}
	
	@Override
	public ExchangeRate addExRate() {
		ExchangeRate rs = new ExchangeRate();
		rs.setDate(dateConvert());
		return rs;
	}
	
	/**
	 * 時間轉換yyyyMMdd
	 * @return
	 */
	private String dateConvert() {
		
	    Date date = new Date();

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	    String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	
	/**
	 * json轉java ExchangeRate物件 只轉特定值
	 * @param ExchangeRate
	 * @return list ExchangeRate
	 */
	private List<ExchangeRate> jsonConvert(String jsonArray){
		JsonParser jsonParser = new JsonParser();
        JsonArray jsonArrayObject = jsonParser.parse(jsonArray).getAsJsonArray();
        List<ExchangeRate> exChangeList = new ArrayList<>();

        for (JsonElement jsonElement : jsonArrayObject) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            BigDecimal USD_NTD = jsonObject.get("USD/NTD").getAsBigDecimal();
            BigDecimal USD_RMB = jsonObject.get("USD/RMB").getAsBigDecimal();
            BigDecimal RMB_NTD = jsonObject.get("RMB/NTD").getAsBigDecimal();
            String date = jsonObject.get("Date").getAsString();

            ExchangeRate entity = new ExchangeRate();
            entity.setUSD_NTD(USD_NTD);
            entity.setUSD_RMB(USD_RMB);
            entity.setRMB_NTD(RMB_NTD);
            entity.setDate(date);
            exChangeList.add(entity);
        }

		return exChangeList;
	}
	
	
	/**
	 * 查詢因子builder
	 * @param date
	 * @return
	 */
	public static Specification<ExchangeRate> searchConditionBuilder(String date) {
		return new Specification<ExchangeRate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ExchangeRate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Set<Predicate> set = new HashSet<>();
				set.add(criteriaBuilder.like(root.get("sn"), root.get("sn")));
				if(!StringUtils.isEmpty(date)) {
					set.add(criteriaBuilder.like(root.get("date"), "%" + date + "%"));
				}
				Predicate[] predicates = new Predicate[set.size()];
				predicates = set.toArray(predicates);
				return criteriaBuilder.and(predicates);
			}
		};}






}
