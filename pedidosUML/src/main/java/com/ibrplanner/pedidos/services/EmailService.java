package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfirmationEmail(Pedido obj);

    void sendMail(SimpleMailMessage msg);
}
