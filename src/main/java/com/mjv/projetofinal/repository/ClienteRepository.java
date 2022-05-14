package com.mjv.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjv.projetofinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
