package com.mjv.projetofinal.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	private String mensagem;
	private String token;
	private String role;
}
