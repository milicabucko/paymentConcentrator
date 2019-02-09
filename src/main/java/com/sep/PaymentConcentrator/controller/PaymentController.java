package com.sep.PaymentConcentrator.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sep.PaymentConcentrator.constants.Constants;
import com.sep.PaymentConcentrator.model.Kupovina;
import com.sep.PaymentConcentrator.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaypalService paypalService;

    private final RestTemplate restTemplate;


    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(
            value = "/payment/execute",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> executePayment(@RequestBody Kupovina kupovina) throws PayPalRESTException {
        String paymentLink = paypalService.getPaymentLink(kupovina);
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/payment/cancel",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void cancelPayPal(){
        //restTemplate.postForObject(databaseUri.getPaymentHandler() + "/payment/cancel", uplataId, Void.class);
        System.out.println("Ne valja");
    }

    @RequestMapping(
            value = "/payment/success/{korisnikId}/{proizvodId}/{tipProizvoda}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void successPayPal(@RequestParam("paymentId")String paymentId,
                              @RequestParam("PayerID")String payerId,
                              @PathVariable Long korisnikId,
                              @PathVariable Long proizvodId,
                              @PathVariable String tipProizvoda
                              ){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                restTemplate.postForObject(Constants.DATABASE_SERVER_URL + "/kupovina/" + korisnikId + "/" + proizvodId + "/" + tipProizvoda, null, String.class);
                System.out.println("Valja");
                return;
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        //restTemplate.postForObject(databaseUri.getPaymentHandler() + "/payment/error", uplataId, Void.class);
    }

    @RequestMapping(
            value = "/payment/success/{korisnikId}/{proizvodId}/{tipProizvoda}/{brojMeseci}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void successPayPalClanarina(@RequestParam("paymentId")String paymentId,
                              @RequestParam("PayerID")String payerId,
                              @PathVariable Long korisnikId,
                              @PathVariable Long proizvodId,
                              @PathVariable String tipProizvoda,
                              @PathVariable Integer brojMeseci
    ){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                restTemplate.postForObject(Constants.DATABASE_SERVER_URL + "/kupovina/" + korisnikId + "/" + proizvodId + "/" + tipProizvoda + "/" + brojMeseci, null, String.class);
                System.out.println("Valja");
                return;
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

    }

}
