package com.tiktop.services;

import java.util.List;

import com.tiktop.model.Paymentinfo;

public interface PaymentinfoService {

	void save(Paymentinfo paymentinfo);

	List<Paymentinfo> findPaymentinfo(Paymentinfo paymentinfo);

	void delete(Integer id);

	void update(Paymentinfo paymentinfo);
}
