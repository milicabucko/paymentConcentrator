package com.sep.PaymentConcentrator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class PCUtil {

    private static final Logger logger = LoggerFactory.getLogger(PCUtil.class);

    private static Integer paymentConcentratorPortNumber1 = 9000;
    private static Integer paymentConcentratorPortNumber2 = 9001;

    public static String paymentConcentratorServerURL1 =  "https://localhost:9000";
    public static String paymentConcentratorServerURL2 =  "https://localhost:9001";

    public static String paymentConcentratorAvailable() {
        try (Socket ignored = new Socket("localhost", paymentConcentratorPortNumber1)) {
            logger.info("\n\t\tSaljem zahtev na prvi server (9000).\n");
            return paymentConcentratorServerURL1;
        } catch (IOException ignored) {
            logger.info("\n\t\tSaljem zahtev na drugi server (9001).\n");
            return paymentConcentratorServerURL2;
        }
    }

}
