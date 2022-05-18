package com.mjv.projetofinal.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjv.projetofinal.dto.LoginDto;
import com.mjv.projetofinal.dto.LoginResponseDto;
import com.mjv.projetofinal.model.cadastro.Cliente;
import com.mjv.projetofinal.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	public void insert(Cliente cliente) {
		repository.save(cliente);
	}

	public List<Cliente> getAll() {
		return repository.findAll();
	}

	public Cliente findByToken(String token) {
		return repository.findByToken(token);
	}

	public LoginResponseDto login(LoginDto logindto) {
		LoginResponseDto login = new LoginResponseDto();
		Cliente c = repository.findByEmailAndSenha(logindto.getEmail(), logindto.getSenha());
		if (!Objects.isNull(c)) {
			if (Objects.isNull(c.getToken())) {
				c.setToken(UUID.randomUUID().toString());
				repository.save(c);
			}

			login.setMensagem("Logado com sucesso!");
			login.setToken(c.getToken());
			login.setRole(c.getRole().toString());
			return login;
		}
		login.setMensagem("Falha ao logar!");
		login.setToken("");
		return login;
	}

	public void logout(String token) {
		Cliente c = repository.findByToken(token);
		c.setToken(null);
		repository.save(c);
	}
}
