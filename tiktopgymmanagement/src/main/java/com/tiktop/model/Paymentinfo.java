package com.tiktop.model;

import javax.validation.constraints.NotNull;

public class Paymentinfo {
	public Integer paymentinfoId;
	@NotNull
	public String paymentPartnerName;
	@NotNull
	public String paymentAc;
	
	public Integer getPaymentinfoId() {
		return paymentinfoId;
	}
	public void setPaymentinfoId(Integer paymentinfoId) {
		this.paymentinfoId = paymentinfoId;
	}
	public String getPaymentPartnerName() {
		return paymentPartnerName;
	}
	public void setPaymentPartnerName(String paymentPartnerName) {
		this.paymentPartnerName = paymentPartnerName;
	}
	public String getPaymentAc() {
		return paymentAc;
	}
	public void setPaymentAc(String paymentAc) {
		this.paymentAc = paymentAc;
	}
}
