package com.mjv.projetofinal.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private String rg;
	private String email;
	private String senha;
	private Long whatsapp;
	@Enumerated(EnumType.STRING)
	private Role role = Role.COMUM;
	@JsonIgnore
	private String token;
	
	
	//@Embedded
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email);
	}


	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	
}
