package com.mjv.projetofinal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mjv.projetofinal.dto.PedidoAdicionarDto;
import com.mjv.projetofinal.dto.PedidoBuscaCliente;
import com.mjv.projetofinal.model.pedido.Pedido;
import com.mjv.projetofinal.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	
	@PostMapping
	public void insert(@RequestBody PedidoAdicionarDto pedido, @RequestParam("token") String token){
		service.save(pedido, token);
	}
	
	@GetMapping
	public PedidoBuscaCliente getPedidosItens(@RequestParam("token") String token) {
		return service.getPedidoByCliente(token);
	}
	
	@GetMapping("/all")
	public List<Pedido> getAllPedidos(@RequestParam("token") String token) {
		return service.getAllPedidos(token);
	}
	
	@PostMapping("{status}")
	public void alterarPedido(@PathVariable("status") String status, @RequestParam("token") String token) {
		service.alterarPedido(status, token);
	}
	
	@DeleteMapping("/delete/{pedidoItemId}")
	public void deleteCarrinhoItem(@PathVariable("pedidoItemId") Integer itemId, @RequestParam("token") String token) {
		service.deleteItem(itemId, token);
	}
	
}
