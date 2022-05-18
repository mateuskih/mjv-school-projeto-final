package com.mjv.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.cadastro.Cliente;
import com.mjv.projetofinal.model.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	Pedido findByCliente(Cliente cliente);
}
