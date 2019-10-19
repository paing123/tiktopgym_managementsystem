package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Paymentinfo;

public interface PaymentinfoDao {

	void save(Paymentinfo paymentinfo);
	
	 List<Paymentinfo> findPaymentinfo(Paymentinfo paymentinfo);
	 
	 void delete(Integer id);
	 
	 void update(Paymentinfo paymentinfo);
}
