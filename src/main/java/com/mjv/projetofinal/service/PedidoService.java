package com.mjv.projetofinal.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjv.projetofinal.dto.PedidoAdicionarDto;
import com.mjv.projetofinal.dto.PedidoBuscaCliente;
import com.mjv.projetofinal.model.cadastro.Cliente;
import com.mjv.projetofinal.model.pedido.Pedido;
import com.mjv.projetofinal.model.pedido.PedidoItem;
import com.mjv.projetofinal.model.pedido.Produto;
import com.mjv.projetofinal.model.pedido.Status;
import com.mjv.projetofinal.repository.ClienteRepository;
import com.mjv.projetofinal.repository.PedidoItemRepository;
import com.mjv.projetofinal.repository.PedidoRepository;
import com.mjv.projetofinal.repository.ProdutoRepository;
import com.mjv.projetofinal.util.Validators;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoItemRepository itemRepository;

	public void save(PedidoAdicionarDto pedidoAdd, String token) {
		Cliente cliente = clienteRepository.findByToken(token);
		Pedido pedido = repository.findByCliente(cliente);
		PedidoItem novoItem = new PedidoItem();
		
		Produto produto = produtoRepository.findById(pedidoAdd.getProdutoId()).get();
		
		BigDecimal total = new BigDecimal(0);
		BigDecimal subtotal = new BigDecimal(0);
		
		if(Objects.isNull(pedido)) {
			pedido = new Pedido();
		}
		
		novoItem.setProduto(produto);
		novoItem.setQuantidade(pedidoAdd.getQuantidade());
		
		List<PedidoItem> itens = new ArrayList<>();
		
		if(!Objects.isNull(pedido.getItens())) {
			itens = pedido.getItens();
			itens.add(novoItem);
			
			for(PedidoItem item: pedido.getItens()) {
				subtotal = new BigDecimal(item.getQuantidade()).multiply(item.getProduto().getPreco());
				item.setSubtotal(subtotal);
				total = Validators.add(total, subtotal);
			}
		}
		else {
			itens.add(novoItem);
			subtotal = new BigDecimal(novoItem.getQuantidade()).multiply(produto.getPreco());
			novoItem.setSubtotal(subtotal);
			total = Validators.add(total, subtotal);
		}
				
		pedido.setItens(itens);
		pedido.setTotal(total);
		pedido.setCliente(cliente);
		pedido.setCreatedAt(LocalDateTime.now());
		pedido.setStatus(Status.NOVO);
		repository.save(pedido);
		
	}
	
	public PedidoBuscaCliente getPedidoByCliente(String token) {
		Cliente cliente = clienteRepository.findByToken(token);
		Pedido pedido = repository.findByCliente(cliente);
		
		PedidoBuscaCliente carrinho = new PedidoBuscaCliente();
		if(!Objects.isNull(pedido.getItens())) {
			carrinho.setCarrinhoItens(pedido.getItens());
		}
		carrinho.setTotal(pedido.getTotal());
		carrinho.setStatus(pedido.getStatus().toString());
		return carrinho;
	}
	
	public List<Pedido> getAllPedidos(String token) {
		Cliente cliente = clienteRepository.findByToken(token);
		if(cliente.getRole().toString().equals("ADMIN")) {
			return repository.findAll();
		}
		return null;
	}
	
	public void deleteItem(Integer itemId, String token) {
		Cliente cliente = clienteRepository.findByToken(token);
		PedidoItem deleteItem = itemRepository.findById(itemId).get();
		
	
		BigDecimal total = new BigDecimal(0);
		BigDecimal subtotal = new BigDecimal(0);
		
		itemRepository.delete(deleteItem);
		Pedido pedido = repository.findByCliente(cliente);
		
		if(!Objects.isNull(pedido.getItens())) {
			for(PedidoItem item: pedido.getItens()) {
				subtotal = new BigDecimal(item.getQuantidade()).multiply(item.getProduto().getPreco());
				item.setSubtotal(subtotal);
				total = Validators.add(total, subtotal);
			}
		}
		pedido.setTotal(total);
		repository.save(pedido);
	}
	
	public void alterarPedido(String status, String token) {
		Cliente cliente = clienteRepository.findByToken(token);
		Pedido pedido = repository.findByCliente(cliente);
		if(status.equals("fechar")) {
			pedido.setStatus(Status.PROCESSANDO);
		}
		else if(status.equals("entregue")) {
			pedido.setStatus(Status.ENTREGUE);
		}
		else if(status.equals("cancelar")) {
			pedido.setStatus(Status.CANCELADO);
		}
		repository.save(pedido);
	}
	

}
