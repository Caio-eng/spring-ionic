package com.pay.estcompra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.estcompra.domain.Cliente;
import com.pay.estcompra.repositories.ClienteRepository;
import com.pay.estcompra.services.exceptions.ObjectNotFountException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Cliente não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
