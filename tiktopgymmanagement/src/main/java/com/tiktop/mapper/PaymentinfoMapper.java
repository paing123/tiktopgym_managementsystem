package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Paymentinfo;

@Mapper
public interface PaymentinfoMapper {
	public void save(@Param("paymentinfo") Paymentinfo paymentinfo);
	
	public List<Paymentinfo> findPaymentinfo(@Param("paymentinfo") Paymentinfo paymentinfo);
	
	public void update(@Param("paymentinfo") Paymentinfo paymentinfo);
	
	public void delete(@Param("id") Integer id);
}
