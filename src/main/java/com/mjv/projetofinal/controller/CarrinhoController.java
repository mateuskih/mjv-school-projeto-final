package com.mjv.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.CarrinhoItemDto;
import com.mjv.projetofinal.model.Carrinho;
import com.mjv.projetofinal.model.Cliente;
import com.mjv.projetofinal.repository.CarrinhoRepository;
import com.mjv.projetofinal.repository.ClienteRepository;
import com.mjv.projetofinal.repository.ProdutoRepository;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public void insert(@RequestBody CarrinhoItemDto item, @RequestParam("token") String token) {
		Cliente c = clienteRepository.findByToken(token);
		Carrinho car = new Carrinho();
		car.setProduto(produtoRepository.findById(item.getId()).get());
		car.setQuantidade(item.getQuantidade());
		car.setCliente(c);
		carrinhoRepository.save(car);
	}
	
}


