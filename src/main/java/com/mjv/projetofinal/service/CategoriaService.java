package com.mjv.projetofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjv.projetofinal.model.cadastro.Categoria;
import com.mjv.projetofinal.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	public void insert(Categoria categoria) {
		repository.save(categoria);
	}
	
	public List<Categoria> getAll(){
		return repository.findAll();
	}
	
	public void updateCategoria(Integer categoriaId, Categoria updateCategoria) {
		Categoria categoria = repository.getById(categoriaId);
		categoria.setNome(updateCategoria.getNome());
		categoria.setDescricao(updateCategoria.getDescricao());
		categoria.setImagemUrl(updateCategoria.getImagemUrl());
		repository.save(categoria);
	}
	
	public void delete (Integer categoriaId) {
		repository.deleteById(categoriaId);
	}
}
