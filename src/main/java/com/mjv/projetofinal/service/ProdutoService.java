package com.mjv.projetofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjv.projetofinal.dto.ProdutoDto;
import com.mjv.projetofinal.model.pedido.Categoria;
import com.mjv.projetofinal.model.pedido.Produto;
import com.mjv.projetofinal.repository.CategoriaRepository;
import com.mjv.projetofinal.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public void insert(ProdutoDto produtodto) {
		Categoria cat = categoriaRepository.getById(produtodto.getCategoriaId());
		Produto prod = new Produto();
		prod.setNome(produtodto.getNome());
		prod.setDescricao(produtodto.getDescricao());
		prod.setPreco(produtodto.getPreco());
		prod.setImagemUrl(produtodto.getImagemUrl());
		prod.setCategoria(cat);
		produtoRepository.save(prod);
	}
	
	public List<Produto> getAll(){
		return produtoRepository.findAll();
	}
	
	public Produto getById(Integer produtoId){
		return produtoRepository.findById(produtoId).orElse(null);
	}
	
	public void update(Integer produtoId,ProdutoDto updateProduto){
		Produto prod = produtoRepository.getById(produtoId);
		prod.setNome(updateProduto.getNome());
		prod.setDescricao(updateProduto.getDescricao());
		prod.setPreco(updateProduto.getPreco());
		prod.setImagemUrl(updateProduto.getImagemUrl());
		prod.setCategoria(categoriaRepository.getById(updateProduto.getCategoriaId()));
		produtoRepository.save(prod);
	}
	
	public void delete(Integer produtoId) {
		produtoRepository.deleteById(produtoId);
	}
	
}
