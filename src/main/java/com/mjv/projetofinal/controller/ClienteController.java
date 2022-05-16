package com.mjv.projetofinal.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.LoginDto;
import com.mjv.projetofinal.dto.LoginResponseDto;
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
	public Cliente findByToken(@RequestBody String token){
		return repository.findByToken(token);
	}
	
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginDto logindto){
		//Cliente cliente = repository.findByEmailAndSenha(logindto.getEmail(), logindto.getSenha());
		//return !Objects.isNull(cliente);
		LoginResponseDto login = new LoginResponseDto();
		Cliente c = repository.findByEmailAndSenha(logindto.getEmail(), logindto.getSenha());
		if(!Objects.isNull(c)) {
			c.setToken(UUID.randomUUID().toString());
			repository.save(c);
			login.setMensagem("Logado com sucesso!");
			login.setToken(c.getToken());
			login.setRole(c.getRole().toString());
			return login;
		}
		login.setMensagem("Falha ao logar!");
		login.setToken("");
		return login;
	}
	
	@PostMapping("/logout")
	public void logout(@RequestBody String token){
		Cliente c = repository.findByToken(token);
		c.setToken(null);
		repository.save(c);
	}
}
