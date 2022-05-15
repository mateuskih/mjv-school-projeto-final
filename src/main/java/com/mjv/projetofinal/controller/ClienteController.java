package com.mjv.projetofinal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.LoginDto;
import com.mjv.projetofinal.model.Cliente;
import com.mjv.projetofinal.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository repository;
	
	
	@PostMapping
	public void insert(@RequestBody Cliente cliente) {
		repository.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/getCliente")
	public Cliente findByToken(@RequestBody String email){
		return repository.findByToken(email);
	}
	
	@PostMapping("/login")
	public Cliente login(@RequestBody LoginDto logindto){
		//Cliente cliente = repository.findByEmailAndSenha(logindto.getEmail(), logindto.getSenha());
		//return !Objects.isNull(cliente);
		Cliente c = repository.findByEmailAndSenha(logindto.getEmail(), logindto.getSenha());
		c.setToken(UUID.randomUUID().toString());
		return c;
	}
	
	@PostMapping("/logout")
	public void logout(@RequestBody String token){
		Cliente c = repository.findByToken(token);
		c.setToken(null);
	}
}
