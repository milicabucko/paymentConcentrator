package com.sep.PaymentConcentrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sep.PaymentConcentrator.model.Payment;
import com.sep.PaymentConcentrator.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	
	private PaymentRepository paymentRepository;
	
	public PaymentService(PaymentRepository paymentRepository){
		this.paymentRepository = paymentRepository;
	}
	
	public void savePayment(Payment payment){
		paymentRepository.save(payment);
	}
	
	public Payment getById(Long id){
		return paymentRepository.getOne(id);
	}

}
