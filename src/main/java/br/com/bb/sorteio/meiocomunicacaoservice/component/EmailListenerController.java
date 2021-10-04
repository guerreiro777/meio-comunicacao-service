package br.com.bb.sorteio.meiocomunicacaoservice.component;

import br.com.bb.sorteio.meiocomunicacaoservice.service.AsyncMessageService;
import br.com.bb.sorteio.meiocomunicacaoservice.service.MailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Log
@Component
public class EmailListenerController {

    private static final String EMAIL_REGISTERED_CUSTOMER_QUEUE_NAME = "send-email-registered-customer";
    private static final String EMAIL_WINNING_CUSTOMER_QUEUE_NAME = "send-email-winning-customer";

    @Autowired
    MailService mailService;

    @Autowired
    AsyncMessageService asyncMessageService;

    @JmsListener(destination = EMAIL_REGISTERED_CUSTOMER_QUEUE_NAME, containerFactory = "myFactory")
    public void listenEmailMessageFromRegisteredCustomer(final String cliente) {
        log.log(Level.INFO, "Fila recebida: " + EMAIL_REGISTERED_CUSTOMER_QUEUE_NAME + " " + cliente);
        mailService.sendMail(cliente);
        asyncMessageService.registerEmailMessageSentSuccessfully(cliente);
    }

    @JmsListener(destination = EMAIL_WINNING_CUSTOMER_QUEUE_NAME, containerFactory = "myFactory")
    public void listenEmailMessageFromWinningCustomer(final String cliente) {
        log.log(Level.INFO, "Fila recebida: " + EMAIL_WINNING_CUSTOMER_QUEUE_NAME + " " + cliente);
        mailService.sendMailToCustomerWinning(cliente);
    }
}
