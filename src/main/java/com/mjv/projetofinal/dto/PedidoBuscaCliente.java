package com.mjv.projetofinal.dto;

import java.math.BigDecimal;
import java.util.List;

import com.mjv.projetofinal.model.pedido.PedidoItem;

import lombok.Data;

@Data
public class PedidoBuscaCliente {
	private List<PedidoItem> carrinhoItens;
	private BigDecimal total;
	private String status;
}
