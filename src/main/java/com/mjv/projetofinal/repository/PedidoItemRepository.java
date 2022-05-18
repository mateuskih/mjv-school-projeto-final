package com.mjv.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.pedido.PedidoItem;


public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer>{
}
