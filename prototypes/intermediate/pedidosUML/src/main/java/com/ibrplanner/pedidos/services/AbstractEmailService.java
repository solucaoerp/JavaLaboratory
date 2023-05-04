package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);

        sendMail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();

        // destinatario
        sm.setTo(obj.getCliente().getEmail());

        // remetente
        sm.setFrom(sender);

        // assunto
        sm.setSubject("Pedido confirmado! Código: " + obj.getId());

        // data (horário do servidor)
        sm.setSentDate(new Date(System.currentTimeMillis()));

        // corpo do email
        sm.setText(obj.toString());
        return sm;
    }
}
