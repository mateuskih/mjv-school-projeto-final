package com.mjv.projetofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.Carrinho;
import com.mjv.projetofinal.model.Cliente;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
	List<Carrinho> findAllByCliente(Cliente cliente);
}
