package com.mjv.projetofinal.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.mjv.projetofinal.dto.CarrinhoAdicionadrItemDto;
import com.mjv.projetofinal.dto.CarrinhoDto;
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
	public void insert(@RequestBody CarrinhoAdicionadrItemDto item, @RequestParam("token") String token) {
		Cliente c = clienteRepository.findByToken(token);
		Carrinho car = new Carrinho();
		car.setProduto(produtoRepository.findById(item.getProdutoId()).get());
		car.setQuantidade(item.getQuantidade());
		car.setCliente(c);
		carrinhoRepository.save(car);
	}
	
	@GetMapping
	public CarrinhoDto getCarrinhosItens(@RequestParam("token") String token) {
		Cliente c = clienteRepository.findByToken(token);
		List<Carrinho> listaCarrinho = carrinhoRepository.findAllByCliente(c);
		
		List<CarrinhoItemDto> itensCarrinho = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		
		for(Carrinho car: listaCarrinho) {
			CarrinhoItemDto carrinhoItemDto = new CarrinhoItemDto(car);
			total = total.add(new BigDecimal(carrinhoItemDto.getQuantidade()).multiply(car.getProduto().getPreco()));
			itensCarrinho.add(carrinhoItemDto);
		}
		
		CarrinhoDto carrinhoDto = new CarrinhoDto();
		carrinhoDto.setTotal(total);
		carrinhoDto.setCarrinhoItens(itensCarrinho);
		return carrinhoDto;
		
	}
	
	@DeleteMapping("/delete/{carrinhoItemId}")
	public void deleteCarrinhoItem(@PathVariable("carrinhoItemId") Integer itemId, @RequestParam("token") String token) {
		Cliente c = clienteRepository.findByToken(token);
		Carrinho car = carrinhoRepository.findById(itemId).get();
		
		carrinhoRepository.delete(car);
	}
}


