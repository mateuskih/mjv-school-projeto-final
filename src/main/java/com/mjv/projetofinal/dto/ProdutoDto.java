package com.mjv.projetofinal.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoDto {
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String imagemUrl;
	private Integer categoriaId;
	
}
