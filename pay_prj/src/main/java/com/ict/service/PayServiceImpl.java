package com.ict.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.domain.PayVO;
import com.ict.mapper.PayMapper;

public class PayServiceImpl implements PayService {
	
	@Autowired
	private PayMapper mapper;
	
	@Override
	public void insertPay(PayVO vo) {
		mapper.insertPay(vo);
	}
	

}
