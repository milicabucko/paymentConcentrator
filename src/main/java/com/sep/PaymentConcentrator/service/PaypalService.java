package com.sep.PaymentConcentrator.service;

import java.util.ArrayList;
import java.util.List;

import com.sep.PaymentConcentrator.config.PaypalConfig;
import com.sep.PaymentConcentrator.constants.Constants;
import com.sep.PaymentConcentrator.model.Kupovina;
import com.sep.PaymentConcentrator.util.PCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {

    private final PaypalConfig payPalConfig;

    @Autowired
    public PaypalService(PaypalConfig payPalConfig) {
        this.payPalConfig = payPalConfig;
    }

    public String getPaymentLink(Kupovina kupovina) throws PayPalRESTException {
        Payment payment = this.createPayment(
                kupovina.getCena(),
                "USD",
                "paypal",
                "sale",
                "Kupovina proizvoda",
                PCUtil.paymentConcentratorAvailable() + "/payment/cancel",
                PCUtil.paymentConcentratorAvailable() +"/payment/success",
                kupovina.getProizvodId(),
                kupovina.getTipProizvoda(),
                kupovina.getKorisnikId(),
                kupovina.getBrojMeseci());
        for(Links links : payment.getLinks()){
            if(links.getRel().equals("approval_url")){
                return links.getHref();
            }
        }
        return PCUtil.paymentConcentratorAvailable() + "/payment/cancel";
    }

    public Payment createPayment(double iznos, String valuta, String metod, String svrha,
                                 String opis, String cancelUrl, String successUrl, Long proizvodId, String tipProizvoda, Long korisnikId, Integer brojMeseci) throws PayPalRESTException {
        Amount amount = new Amount(valuta, String.format("%.2f", iznos));

        Transaction transaction = new Transaction();
        transaction.setDescription(opis);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(metod);

        Payment payment = new Payment(svrha, payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        if (brojMeseci != -1) {
            redirectUrls.setReturnUrl(successUrl + '/' + korisnikId + '/' + proizvodId + '/' + tipProizvoda + '/' + brojMeseci);
        }
        else {
            redirectUrls.setReturnUrl(successUrl + '/' + korisnikId + '/' + proizvodId + '/' + tipProizvoda);
        }
        payment.setRedirectUrls(redirectUrls);

        return payment.create(payPalConfig.apiContext());
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(payPalConfig.apiContext(), paymentExecution);
    }



}
