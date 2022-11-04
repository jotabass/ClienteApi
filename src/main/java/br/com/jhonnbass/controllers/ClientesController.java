package br.com.jhonnbass.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonnbass.dtos.ClienteGetDto;
import br.com.jhonnbass.dtos.ClientePostDto;
import br.com.jhonnbass.dtos.ClientePutDto;
import br.com.jhonnbass.entities.Cliente;
import br.com.jhonnbass.repositories.ClienteRepository;
import br.com.jhonnbass.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Clientes")
@RestController
public class ClientesController {
	
    @Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ClienteService clienteService;

	@ApiOperation("Serviço para cadastrar clientes")
	@PostMapping("/api/clientes")
	public ResponseEntity<String> post(@RequestBody ClientePostDto dto) {

		try {
			Cliente cliente = new Cliente();

			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setCpf(dto.getCpf());
			clienteService.cadastrar(cliente);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Cliente " + cliente.getNome() + ", cadastrado com sucesso");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

//-------------------------------------------------------------------------------------------------------------------	

	@ApiOperation("Serviço para atualizar os clientes")
	@PutMapping("/api/clientes")
	public ResponseEntity<String> put(@RequestBody ClientePutDto dto) {

		try {
			Cliente cliente = new Cliente();

			cliente.setIdCliente(dto.getIdCliente());
			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setCpf(dto.getCpf());
			clienteService.atualizar(cliente); // método chamado na classe service.

			return ResponseEntity.status(HttpStatus.OK)
					.body("Cliente " + cliente.getNome() + ", atualizado com sucesso");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	// -------------------------------------------------------------------------------------------------------------------

	@ApiOperation("Serviço para excluir clientes.")
	@DeleteMapping("/api/cliente/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

		try {
			clienteService.excluir(id);

			return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	// -------------------------------------------------------------------------------------------------------------------

	@ApiOperation("Serviço para consultar todos clientes cadastrados.")
	@GetMapping("/api/clientes")
	public ResponseEntity<List<ClienteGetDto>> getAll() {

		try {

			List<ClienteGetDto> lista = new ArrayList<ClienteGetDto>();

			for (Cliente cliente : clienteService.consultar()) {

				ClienteGetDto dto = new ClienteGetDto();

				dto.setIdCliente(cliente.getIdCliente());
				dto.setNome(cliente.getNome());
				dto.setEmail(cliente.getEmail());
				dto.setCpf(cliente.getCpf());

				lista.add(dto);
			}

			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	@ApiOperation("Método para consultar uma pessoa baseado  no ID (identificador).")
	@GetMapping("/api/cliente/{id}")
	public ResponseEntity<Object> getById(@PathVariable() Integer id) {
		try {	

			clienteService.consultarId(id);
					
			return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id));
			

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
}









