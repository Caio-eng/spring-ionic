package com.pay.estcompra.services;

import org.springframework.mail.SimpleMailMessage;

import com.pay.estcompra.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
