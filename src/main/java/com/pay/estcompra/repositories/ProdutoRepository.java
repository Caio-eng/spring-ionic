package com.pay.estcompra.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pay.estcompra.domain.Categoria;
import com.pay.estcompra.domain.Produto;

@Repository
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE "
//			+ "obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
//			@Param("nome") String nome, 
//			@Param("categorias") List<Categoria> categorias, 
//			Pageable pageRequest);
	
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
			String nome, 
			List<Categoria> categorias, 
			Pageable pageRequest);

}
