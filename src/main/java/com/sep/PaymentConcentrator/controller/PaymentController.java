package com.sep.PaymentConcentrator.controller;

import com.paypal.base.rest.PayPalRESTException;
import com.sep.PaymentConcentrator.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaypalService paypalService;

    @RequestMapping(
            value = "/payment/hello",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> hello() throws PayPalRESTException {
        String paymentLink = paypalService.getPaymentLink();
        return new ResponseEntity<>("Zdravo payment", HttpStatus.OK);
    }

}
