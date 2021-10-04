package br.com.bb.sorteio.meiocomunicacaoservice.component;

import br.com.bb.sorteio.meiocomunicacaoservice.service.AsyncMessageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Log
@Component
public class SmsListenerController {

    private static final String SMS_REGISTERED_CUSTOMER_QUEUE_NAME = "send-sms-registered-customer";
    private static final String SMS_WINNING_CUSTOMER_QUEUE_NAME = "send-sms-winning-customer";

    @Autowired
    AsyncMessageService asyncMessageService;

    @JmsListener(destination = SMS_REGISTERED_CUSTOMER_QUEUE_NAME, containerFactory = "myFactory")
    public void listenSmsMessageFromRegisteredCustomer(final String cliente) {
        log.log(Level.INFO, "Fila recebida: " + SMS_REGISTERED_CUSTOMER_QUEUE_NAME + " " + cliente);
        asyncMessageService.registerSmsMessageSentSuccessfully(cliente);
    }

    @JmsListener(destination = SMS_WINNING_CUSTOMER_QUEUE_NAME, containerFactory = "myFactory")
    public void listenSmsMessageFromWinningCustomer(final String cliente) {
        log.log(Level.INFO, "Fila recebida: " + SMS_WINNING_CUSTOMER_QUEUE_NAME + " " + cliente);
    }
}
