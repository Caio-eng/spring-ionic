package com.pay.estcompra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.estcompra.domain.Pedido;
import com.pay.estcompra.repositories.PedidoRepository;
import com.pay.estcompra.services.exceptions.ObjectNotFountException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Pedido não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
