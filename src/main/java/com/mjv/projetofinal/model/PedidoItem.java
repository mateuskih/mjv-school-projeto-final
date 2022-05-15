package com.mjv.projetofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_item")
public class PedidoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double quantidade;
	@Column(name = "produto_id")
	private Integer produtoId;
	@Column(name = "valor_unitario")
	private Double valorUnitario;
	private Double subtotal;

}
