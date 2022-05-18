package com.mjv.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.pedido.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
}
