package com.mjv.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.model.Pedido;
import com.mjv.projetofinal.repository.PedidoItemRepository;
import com.mjv.projetofinal.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PedidoItemRepository repositoryPedidoItem;
	
	@PostMapping
	public void insert(@RequestBody Pedido pedido) {
		repository.save(pedido);
	}
	
	@GetMapping
	public List<Pedido> getAll(){
		return repository.findAll();
	}
	
	@PutMapping("/item/{id}")
	public void removerItem(@PathVariable ("id") Integer idPedidoItem){
		repositoryPedidoItem.deleteById(idPedidoItem);
	}
	
}
