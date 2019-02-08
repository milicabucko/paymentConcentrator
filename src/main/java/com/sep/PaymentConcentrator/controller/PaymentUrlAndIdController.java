package com.sep.PaymentConcentrator.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.PaymentConcentrator.dto.MerchantDTO;
import com.sep.PaymentConcentrator.dto.PaymentUrlIdDTO;
import com.sep.PaymentConcentrator.model.Kupovina;
import com.sep.PaymentConcentrator.model.NaucnaCentrala;
import com.sep.PaymentConcentrator.service.NaucnaCentralaService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentUrlAndIdController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentUrlAndIdController.class);

	@Autowired
	private NaucnaCentralaService naucnaCentralaService;
	
	@RequestMapping(value = "/urlandid", method = RequestMethod.POST)
    public PaymentUrlIdDTO handleBuy(@RequestBody Kupovina kupovina) {

		logger.info("Forwarding paymentId and url request to Acquirer from NC");

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        PaymentUrlIdDTO paymentUrlIdDTO = new PaymentUrlIdDTO();
        MerchantDTO merchantDTO = setMerchantDto(kupovina);

        try {

        	logger.info("Forwarding request to bank");
             //step 2
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MerchantDTO> entity = new HttpEntity<>(merchantDTO, headers);
            paymentUrlIdDTO = client.postForObject("https://localhost:7575/api/urlandid", entity,
                    PaymentUrlIdDTO.class);
        } catch (Exception e) {
        	logger.info("Doslo je do greske");
        	paymentUrlIdDTO.setPaymentId(null);
            paymentUrlIdDTO.setUrl("https://localhost:4200/bankPaymentError");
        }
        return paymentUrlIdDTO; 
    }
	
	private MerchantDTO setMerchantDto(Kupovina k) {
		MerchantDTO mdto = new MerchantDTO();
		Random randomGenerator = new Random();
		mdto.setMerchantId(k.getTenantID().toString());
		NaucnaCentrala centrala = naucnaCentralaService.getById(k.getTenantID());
		mdto.setMerchantPassword(centrala.getMerchantPassword());
		mdto.setAmount(BigDecimal.valueOf(k.getCena()));
		mdto.setMerchantOrderID(randomGenerator.nextInt(1000));
		mdto.setMerchantTimestamp(new Timestamp(System.currentTimeMillis()));
		return mdto;
	}

}
