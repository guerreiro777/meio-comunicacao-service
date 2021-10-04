package br.com.bb.sorteio.meiocomunicacaoservice.service;

import br.com.bb.sorteio.meiocomunicacaoservice.utils.JsonUtil;
import com.google.gson.JsonObject;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
@Log
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(final String clienteMessage) {
        JsonObject cliente = JsonUtil.stringToJson(clienteMessage);
        final String nmCliente = cliente.get("nmCliente").getAsString();
        final String emailTo = cliente.get("strEmail").getAsString();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Confirmação de cadastro para o Mega Sorteio!");
            final String text = "Olá " + nmCliente + ", " +
                    "você agora está cadastrado e participando do sorteio. " +
                    "Desejamos a você boa sorte.";
            message.setText(text);
            message.setFrom("thierrysouza2007@gmail.com");
            message.setTo(emailTo);
            javaMailSender.send(message);
            log.info("E-mail enviado com sucesso");
        } catch (Exception e) {
            log.log(Level.WARNING, "Erro ao enviar e-mail ao cliente", e);
        }
    }

    public void sendMailToCustomerWinning(final String clienteMessage) {
        JsonObject cliente = (JsonObject) JsonUtil.stringToJson(clienteMessage);
        final String nmCliente = cliente.get("nmCliente").getAsString();
        final String emailTo = cliente.get("strEmail").getAsString();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Você é o grande vencedor!");
            final String text = "Parabéns " + nmCliente + ", " +
                    "você é o grande vencedor do sorteio!!!!";
            message.setText(text);
            message.setFrom("thierrysouza2007@gmail.com");
            message.setTo(emailTo);
            javaMailSender.send(message);
            log.info("E-mail enviado com sucesso ao vencedor do sorteio");
        } catch (Exception e) {
            log.log(Level.WARNING, "Erro ao enviar e-mail ao cliente", e);
        }
    }
}
