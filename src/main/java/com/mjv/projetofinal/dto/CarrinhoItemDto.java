package com.mjv.projetofinal.dto;

import com.mjv.projetofinal.model.Carrinho;
import com.mjv.projetofinal.model.Produto;

import lombok.Data;

@Data
public class CarrinhoItemDto {
	private Integer id;
	private Integer quantidade;
	private Produto produto;
	
	public CarrinhoItemDto(Carrinho carrinho) {
		this.id = carrinho.getId();
		this.quantidade = carrinho.getQuantidade();
		this.setProduto(carrinho.getProduto());
	}
}
