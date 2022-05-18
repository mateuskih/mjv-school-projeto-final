package com.mjv.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.cadastro.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	Cliente findByEmailAndSenha(String email, String senha);
	Cliente findByToken(String token);
}
