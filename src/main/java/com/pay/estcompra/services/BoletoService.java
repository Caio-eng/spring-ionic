package com.pay.estcompra.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.pay.estcompra.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instateDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instateDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}
}
