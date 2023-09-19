package com.guisi.exchangeRate.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guisi.exchangeRate.entities.ExchangeRate;
import com.guisi.exchangeRate.service.ExchangeRateService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class ExchangeRateController {

	private ExchangeRateService service;
	
	
	public ExchangeRateController(ExchangeRateService service) {
		super();
		this.service = service;
	}
	
	/**
	 * 查匯率資料
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/takeDailyForeignExchangeRatesAPI")
	public String takeDailyForeignExchangeRatesAPI(Model modal) {
		service.takeDailyForeignExchangeRatesAPI();
		return "redirect:" + "/index"; // 重新導向到指定的url
	}

	/**
	 * 首頁
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/index")
	public String index(Model modal) {
		List<ExchangeRate> list = service.findAllExRate();
		modal.addAttribute("exchangeRate",list);
		return "index";
	}
	
	/**
	 * 更新匯率檔初始
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/updateExRatePage")
	public String updateExRatePage(Model modal,@RequestParam("sn") Integer sn) {
		ExchangeRate entity = service.findById(sn);
		modal.addAttribute("entity",entity);
		return "add";
	}
	
	/**
	 * 新增匯率檔初始
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/addExRatePage")
	public String addExRatePage(Model modal) {
		modal.addAttribute("entity",service.addExRate());
		return "add";
	}
	
	/**
	 * 更新匯率檔
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/updateExRate")
	public String updateExRate(Model modal,@Valid @ModelAttribute("entity")ExchangeRate entity,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "輸入有誤");
			return "redirect:" + "/index";
		}
		service.updateExRate(entity);
		return "redirect:" + "/index";
	}
	
	/**
	 * 刪匯率檔
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/deleteExRate")
	public String deleteExRate(@RequestParam("sn") Integer sn) {
		service.deleteExRate(sn);
		return "redirect:" + "/index"; 
	}

	/**
	 * 查匯率檔
	 * @param modal
	 * @return
	 */
	@RequestMapping(path="/findAll")
	public String findAll() {
		service.findAllExRate();
		return "index";
	}
}
