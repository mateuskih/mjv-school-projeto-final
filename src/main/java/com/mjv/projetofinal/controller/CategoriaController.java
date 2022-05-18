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

import com.mjv.projetofinal.model.cadastro.Categoria;
import com.mjv.projetofinal.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	public void insert(@RequestBody Categoria categoria) {
		service.insert(categoria);
	}
	
	@GetMapping
	public List<Categoria> getAll(){
		return service.getAll();
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable ("id") Integer categoriaId, @RequestBody Categoria updateCategoria){
		service.updateCategoria(categoriaId, updateCategoria);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ("id") Integer categoriaId) {
		service.delete(categoriaId);
	}

}
