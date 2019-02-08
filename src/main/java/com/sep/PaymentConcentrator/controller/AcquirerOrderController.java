package com.sep.PaymentConcentrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.PaymentConcentrator.dto.ResponseMessageDTO;
import com.sep.PaymentConcentrator.dto.ResponseMessageDTO.TransactionResult;
import com.sep.PaymentConcentrator.dto.UrlDTO;
import com.sep.PaymentConcentrator.dto.UrlDTO.Status;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AcquirerOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(AcquirerOrderController.class);
	
	@RequestMapping(value = "/payment/pay", method = RequestMethod.POST)
    public ResponseEntity<UrlDTO> handlePaying(@RequestBody ResponseMessageDTO responseMessageDTO) {

		logger.info("Acquirer order payment executed.");

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        UrlDTO dto = new UrlDTO();
        try {

        	logger.info("Forwarding payment to science center");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ResponseMessageDTO> entity = new HttpEntity<>(responseMessageDTO, headers);
            // saljemo u NC obavestenje da je placanje izvrseno
            dto = client.postForObject("https://localhost:7000/api/payment/executed", entity,
            		UrlDTO.class);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (Exception e) {
        	logger.info("A mistake occurred {}.", e.getMessage());
        	dto.setResult(TransactionResult.ERROR);
        	dto.setStatus(Status.FAILED);
        	dto.setUrl("https://localhost:4200/bankError");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }

    }

}
