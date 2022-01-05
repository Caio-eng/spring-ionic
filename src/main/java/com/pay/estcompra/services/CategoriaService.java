package com.pay.estcompra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.estcompra.domain.Categoria;
import com.pay.estcompra.repositories.CategoriaRepository;
import com.pay.estcompra.services.exceptions.ObjectNotFountException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
}
