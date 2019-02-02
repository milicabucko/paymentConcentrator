package com.sep.PaymentConcentrator.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.PaymentConcentrator.dto.BitCoinDTO;
import com.sep.PaymentConcentrator.dto.ResponseBitcoinDTO;

@RestController
@RequestMapping("/api")
public class BitCoinController {
	
	private static final Logger logger = LoggerFactory.getLogger(BitCoinController.class);

	
	@CrossOrigin
	@RequestMapping(
			value = "/bitcoin",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> bitcoin(@RequestBody BitCoinDTO b, HttpServletResponse r) {
		logger.info("Početak bitcoin plaćanja");
		
		Map<String, Object> mapa = new HashMap<String,Object>();
        mapa.put("order_id",UUID.randomUUID().toString());
        mapa.put("price_amount",b.getCena());
        mapa.put("price_currency","USD");
        mapa.put("receive_currency","USD");
        mapa.put("title",b.getProizvodID());
        mapa.put("description","desc");
        mapa.put("callback_url","https://api-sandbox.coingate.com/account/orders"); //TODO:promeniti
        mapa.put("success_url", "http://localhost:9000/responseSuccessBitcoin.html");
        
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //TODO: MILENA namestiti token od korisnika
        headers.add("Authorization", "Token TCCkZmVXFx83JxNiVaQE2Jp74Bz9b1gDhryXEML6");
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String,Object>>(mapa, headers);
        
        ResponseBitcoinDTO response = client.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, ResponseBitcoinDTO.class);
              
        HttpHeaders noviHeaders = new HttpHeaders();
        noviHeaders.add("Authorization", "Token TCCkZmVXFx83JxNiVaQE2Jp74Bz9b1gDhryXEML6");
        noviHeaders.add("Location", response.getPayment_url()); //payment url
        noviHeaders.add("id", response.getId().toString()); //id inicirane transakcije
        noviHeaders.add("uuid", response.getPayment_url().split("invoice/")[1]);
        noviHeaders.add("title", b.getProizvodID().toString()); //naziv casopisa koji se placa
        noviHeaders.add("created_at", response.getCreated_at());
        noviHeaders.add("status", response.getStatus()); //ovde ce uvek biti new
        noviHeaders.add("merchant_order_id", response.getOrder_id()); //uplatilac        
        logger.info("\t\tnoviHeaders: " + noviHeaders.toString() + "\n\n");
        
        HttpEntity<ResponseBitcoinDTO> entity1 = new HttpEntity<ResponseBitcoinDTO>(response, noviHeaders);
        //String odg = client.postForObject(entity1.getHeaders().getLocation(), entity1, String.class); //ne moze da pogodi nas localhost...
        
        //parametri potrebni za kreiranje bitcoin transakcije
        String paymentUrl = response.getPayment_url();
        String idIniciraneTransakcije = response.getId().toString();
        String uuid = response.getPayment_url().split("invoice/")[1];
        String naziv = b.getTipProizvoda();
        String vremeKreiranja = response.getCreated_at();
        String status = response.getStatus();
        String uplatilac = response.getOrder_id();
        String retVal = paymentUrl + ", " + idIniciraneTransakcije + ", " + uuid + ", " + naziv + ", " + vremeKreiranja + ", " + status + ", " + uplatilac;
        //System.out.println("\t\t[bitcoin transakcija] retVal:\n\t\t" + retVal + "\n\n");
        
        logger.info("\n\t\tUspešno završeno plaćanje preko bitcoin-a.\n");
        return new ResponseEntity<>(retVal, noviHeaders, HttpStatus.OK);
	}

}
