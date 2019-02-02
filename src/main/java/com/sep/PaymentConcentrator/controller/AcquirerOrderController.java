package com.sep.PaymentConcentrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.PaymentConcentrator.dto.PaymentInfoDTO;
import com.sep.PaymentConcentrator.dto.ResponseMessageDTO;
import com.sep.PaymentConcentrator.dto.ResponseMessageDTO.TransactionResult;

@RestController
@RequestMapping("/api")
public class AcquirerOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(AcquirerOrderController.class);

	
	@RequestMapping(value = "/payment/pay", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessageDTO> handlePaying(@RequestBody PaymentInfoDTO paymentInfoDTO) {

        System.out.println("Pogodio PC");

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();

        try {

            System.out.println("Prosledjivanje kupovine osiguranja Acquirer banci");
            //step 5
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PaymentInfoDTO> entity = new HttpEntity<>(paymentInfoDTO, headers);
            //TODO responseMessageDto je step 10, ovog poziva NCClient
            responseMessageDTO  = client.postForObject("http://localhost:7575/api/payment/pay", entity,
                    ResponseMessageDTO.class);
            return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Doslo je do greske");
            responseMessageDTO.setResult(TransactionResult.ERROR);
            return new ResponseEntity<>(responseMessageDTO, HttpStatus.BAD_REQUEST);
        }

    }

}
