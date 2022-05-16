package com.mjv.projetofinal.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CarrinhoDto {
	private List<CarrinhoItemDto> carrinhoItens;
	private BigDecimal total;
}
