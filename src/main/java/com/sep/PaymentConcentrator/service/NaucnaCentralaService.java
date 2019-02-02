package com.sep.PaymentConcentrator.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sep.PaymentConcentrator.model.NaucnaCentrala;
import com.sep.PaymentConcentrator.repository.NaucnaCentralaRepository;

@Service
@Transactional
public class NaucnaCentralaService {
	
	private NaucnaCentralaRepository naucnaCentralaRepository;
	
	public NaucnaCentralaService(NaucnaCentralaRepository naucnaCentralaRepository){
		this.naucnaCentralaRepository = naucnaCentralaRepository;
	}
	
	public NaucnaCentrala getById(Long id){
		return naucnaCentralaRepository.getOne(id);
	}

}
