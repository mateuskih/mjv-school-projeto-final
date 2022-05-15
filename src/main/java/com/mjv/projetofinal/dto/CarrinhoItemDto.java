package com.mjv.projetofinal.dto;

import lombok.Data;

@Data
public class CarrinhoItemDto {
	
	private Integer id;
	private Integer produtoId;
	private Integer quantidade;
	
	public CarrinhoItemDto() {}

}
