package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.PaymentinfoMapper;
import com.tiktop.model.Paymentinfo;

@Repository("paymentinfoRepository")
public class PaymentinfoDaoImpl implements PaymentinfoDao {

	@Autowired
	PaymentinfoMapper paymentinfoMapper;
	
	public void save(Paymentinfo paymentinfo) {
		paymentinfoMapper.save(paymentinfo);
	}
	
	public List<Paymentinfo> findPaymentinfo(Paymentinfo paymentinfo) {
		List<Paymentinfo> paymentinfoes = paymentinfoMapper.findPaymentinfo(paymentinfo);
		return paymentinfoes;
	}
	
	public void delete(Integer id) {
		paymentinfoMapper.delete(id);
	}
	
	public void update(Paymentinfo paymentinfo) {
		paymentinfoMapper.update(paymentinfo);
	}
}
