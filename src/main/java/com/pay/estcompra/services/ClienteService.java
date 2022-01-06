package com.pay.estcompra.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pay.estcompra.domain.Cidade;
import com.pay.estcompra.domain.Cliente;
import com.pay.estcompra.domain.Endereco;
import com.pay.estcompra.domain.enums.TipoCliente;
import com.pay.estcompra.dto.ClienteDTO;
import com.pay.estcompra.dto.ClienteNewDTO;
import com.pay.estcompra.repositories.ClienteRepository;
import com.pay.estcompra.repositories.EnderecoRepository;
import com.pay.estcompra.services.exceptions.ObjectNotFountException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Cliente não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerpage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerpage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir porque há pedidos relacionados");
		}
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
