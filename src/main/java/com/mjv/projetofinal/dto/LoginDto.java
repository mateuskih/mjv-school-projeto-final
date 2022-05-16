package com.mjv.projetofinal.dto;

import lombok.Data;

@Data
public class LoginDto {
	private String email;
	private String senha;
	private String role;
}
