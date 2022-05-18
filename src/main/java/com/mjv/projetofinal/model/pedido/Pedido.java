package com.mjv.projetofinal.model.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mjv.projetofinal.model.cadastro.Cliente;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JoinColumn(name = "created_at")
	private LocalDateTime createdAt;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<PedidoItem> itens;
	
}
