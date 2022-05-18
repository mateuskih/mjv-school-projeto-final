package com.mjv.projetofinal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.LoginDto;
import com.mjv.projetofinal.dto.LoginResponseDto;
import com.mjv.projetofinal.model.cadastro.Cliente;
import com.mjv.projetofinal.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	
	@PostMapping
	public void insert(@RequestBody Cliente cliente) {
		service.insert(cliente);
	}
	
	@GetMapping
	public List<Cliente> getAll(){
		return service.getAll();
	}
	
	@PostMapping("/getCliente")
	public Cliente findByToken(@RequestBody String token){
		return service.findByToken(token);
	}
	
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginDto logindto){
		return service.login(logindto);
	}
	
	@PostMapping("/logout")
	public void logout(@RequestBody String token){
		service.logout(token);
	}
}
