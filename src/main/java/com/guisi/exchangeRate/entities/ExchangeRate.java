package com.guisi.exchangeRate.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 */
/**
 * 
 */
@Entity
@Table(name="exchangerate")
public class ExchangeRate {
	
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sn;
	
	/**
	 * 美金/台幣
	 */
	@NotNull
	@Digits(integer=5,fraction=8)
	@Column(name="USD_NTD")
	private BigDecimal USD_NTD;
	
	/**
	 * 人民幣/台幣
	 */
	@NotNull
	@Digits(integer=5,fraction=8)
	@Column(name="RMB_NTD")
	private BigDecimal RMB_NTD;
	
	/**
	 * 美金/人民幣
	 */
	@NotNull
	@Digits(integer=5,fraction=8)
	@Column(name="USD_RMB")
	private BigDecimal USD_RMB;
	
	/**
	 * 
	 */
	@NotNull
	@Column(name="date")
	private String date;
	
	
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public BigDecimal getUSD_NTD() {
		return USD_NTD;
	}
	public void setUSD_NTD(BigDecimal uSD_NTD) {
		USD_NTD = uSD_NTD;
	}
	public BigDecimal getRMB_NTD() {
		return RMB_NTD;
	}
	public void setRMB_NTD(BigDecimal rMB_NTD) {
		RMB_NTD = rMB_NTD;
	}
	public BigDecimal getUSD_RMB() {
		return USD_RMB;
	}
	public void setUSD_RMB(BigDecimal uSD_RMB) {
		USD_RMB = uSD_RMB;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "ExchangeRate [sn=" + sn + ", USD_NTD=" + USD_NTD + ", RMB_NTD=" + RMB_NTD + ", USD_RMB=" + USD_RMB
				+ ", date=" + date + "]";
	}
	
	

}
