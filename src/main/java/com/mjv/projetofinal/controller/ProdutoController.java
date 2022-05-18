package com.mjv.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.ProdutoDto;
import com.mjv.projetofinal.model.pedido.Produto;
import com.mjv.projetofinal.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService service;
	
	
	@PostMapping
	public void insert(@RequestBody ProdutoDto produtodto) {
		service.insert(produtodto);
	}
	
	@GetMapping
	public List<Produto> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Produto getById(@PathVariable ("id") Integer produtoId){
		return service.getById(produtoId);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable ("id") Integer produtoId, @RequestBody ProdutoDto updateProduto){
		service.update(produtoId, updateProduto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ("id") Integer produtoId) {
		service.delete(produtoId);
	}

}
