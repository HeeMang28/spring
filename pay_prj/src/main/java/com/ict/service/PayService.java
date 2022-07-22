package com.ict.service;

import org.springframework.stereotype.Service;

import com.ict.domain.PayVO;

@Service
public interface PayService {
	
	public void insertPay(PayVO vo);

}
