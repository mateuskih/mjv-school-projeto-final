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

import com.mjv.projetofinal.model.Categoria;
import com.mjv.projetofinal.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;
	
	@PostMapping
	public void insert(@RequestBody Categoria categoria) {
		repository.save(categoria);
	}
	
	@GetMapping
	public List<Categoria> getAll(){
		return repository.findAll();
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable ("id") Integer categoriaId, @RequestBody Categoria updateCategoria){
		Categoria cat = repository.getById(categoriaId);
		cat.setNome(updateCategoria.getNome());
		cat.setDescricao(updateCategoria.getDescricao());
		cat.setImagemUrl(updateCategoria.getImagemUrl());
		repository.save(cat);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ("id") Integer categoriaId) {
		repository.deleteById(categoriaId);
	}

}
