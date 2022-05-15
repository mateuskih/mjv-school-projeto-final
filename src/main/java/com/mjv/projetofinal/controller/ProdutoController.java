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
import com.mjv.projetofinal.model.Categoria;
import com.mjv.projetofinal.model.Produto;
import com.mjv.projetofinal.repository.CategoriaRepository;
import com.mjv.projetofinal.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public void insert(@RequestBody ProdutoDto produtodto) {
		Categoria cat = categoriaRepository.getById(produtodto.getCategoriaId());
		Produto prod = new Produto();
		prod.setNome(produtodto.getNome());
		prod.setDescricao(produtodto.getDescricao());
		prod.setPreco(produtodto.getPreco());
		prod.setImagemUrl(produtodto.getImagemUrl());
		prod.setCategoria(cat);
		repository.save(prod);
	}
	
	@GetMapping
	public List<Produto> getAll(){
		return repository.findAll();
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable ("id") Integer produtoId, @RequestBody ProdutoDto updateProduto){
		Produto prod = repository.getById(produtoId);
		prod.setNome(updateProduto.getNome());
		prod.setDescricao(updateProduto.getDescricao());
		prod.setPreco(updateProduto.getPreco());
		prod.setImagemUrl(updateProduto.getImagemUrl());
		prod.setCategoria(categoriaRepository.getById(updateProduto.getCategoriaId()));
		repository.save(prod);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ("id") Integer produtoId) {
		repository.deleteById(produtoId);
	}
	

}
