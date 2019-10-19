package com.tiktop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.PaymentinfoDao;
import com.tiktop.model.Paymentinfo;

@Service("paymentinfoService")
public class PaymentinfoServiceImpl implements PaymentinfoService {

	@Autowired
	PaymentinfoDao paymentinfoDao;

	@Override
	public void save(Paymentinfo paymentinfo) {
		paymentinfoDao.save(paymentinfo);
	}

	@Override
	public List<Paymentinfo> findPaymentinfo(Paymentinfo paymentinfo) {
		return paymentinfoDao.findPaymentinfo(paymentinfo);
	}
	
	@Override
	public void delete(Integer id) {
		paymentinfoDao.delete(id);
	}

	@Override
	public void update(Paymentinfo paymentinfo) {
		paymentinfoDao.update(paymentinfo);
	}
}
