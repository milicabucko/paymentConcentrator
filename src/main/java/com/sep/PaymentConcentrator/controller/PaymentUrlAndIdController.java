package com.sep.PaymentConcentrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.PaymentConcentrator.dto.MerchantDTO;
import com.sep.PaymentConcentrator.dto.PaymentUrlIdDTO;

@RestController
@RequestMapping("/api")
public class PaymentUrlAndIdController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentUrlAndIdController.class);

	
	@RequestMapping(value = "/urlandid", method = RequestMethod.POST)
    public PaymentUrlIdDTO handleBuy(@RequestBody MerchantDTO merchantDTO) {

		logger.info("Forwarding paymentId and url request to Acquirer from NC");

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        PaymentUrlIdDTO paymentUrlIdDTO = new PaymentUrlIdDTO();

        try {

        	logger.info("Forwarding request to bank");
             //step 2
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MerchantDTO> entity = new HttpEntity<>(merchantDTO, headers);
            paymentUrlIdDTO = client.postForObject("http://localhost:7575/api/urlandid", entity,
                    PaymentUrlIdDTO.class);
            return paymentUrlIdDTO;

        } catch (Exception e) {
        	logger.info("Doslo je do greske");
            return null; //TODO: MILENA vrati error URL !!!!
        }
    }

}
