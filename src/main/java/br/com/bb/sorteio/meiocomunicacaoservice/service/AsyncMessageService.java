package br.com.bb.sorteio.meiocomunicacaoservice.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
@Log
public class AsyncMessageService {

    private static final String SMS_QUEUE_NAME = "sms-sent-successfully";
    private static final String EMAIL_QUEUE_NAME = "email-sent-successfully";

    @Autowired
    JmsTemplate jmsTemplate;

    private void createQueue(final String queueName, final String cliente) {
        jmsTemplate.convertAndSend(queueName, cliente);
        log.log(Level.INFO, "Fila cadastrada: " + queueName + " " + cliente);
    }

    public void registerSmsMessageSentSuccessfully (final String cliente) {
        this.createQueue(SMS_QUEUE_NAME, cliente);
    }

    public void registerEmailMessageSentSuccessfully(final String cliente) {
        this.createQueue(EMAIL_QUEUE_NAME, cliente);
    }
}
