package br.com.jhonnbass.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.jhonnbass.components.EmailComponent;
import br.com.jhonnbass.entities.Cliente;
import br.com.jhonnbass.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EmailComponent emailComponent;

	public void cadastrar(Cliente cliente) throws Exception {

		if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
			throw new IllegalArgumentException("O email informado já está cadastrado, tente outro.");
		}
		clienteRepository.save(cliente);

		String assunto = "Parabéns, sua conta foi criada com sucesso (API Clientes)";
		String corpo = "Olá " + cliente.getNome() + "\n\nSua conta foi criada com sucesso."
				+ "\n\nAtt\nEquipe JOTA B Developers";
		emailComponent.enviarMensagem(cliente.getEmail(), assunto, corpo);
	}

	public void atualizar(Cliente cliente) throws Exception {

		// Apenas 2 sendo O ID primeira regra.
		Optional<Cliente> optional = clienteRepository.findById(cliente.getIdCliente());
		if (optional.isEmpty()) { // se optional esta vazio
			throw new IllegalArgumentException("O cliente não foi encontrado, verifique o ID informado.");
		}

		// Regra 2: CPf unico para cada cliente
		Cliente clienteByCpf = clienteRepository.findByCpf(cliente.getCpf());
		if (clienteByCpf != null && clienteByCpf.getIdCliente() != cliente.getIdCliente()) {
			throw new IllegalArgumentException("O CPF informado já está cadastrado para outro cliente.");
		}

		// atualizar no banco de dados
		clienteRepository.save(cliente);
	}

	public void excluir(Integer idCliente) throws Exception {

		// procurando o cliente no banco
		Optional<Cliente> optional = clienteRepository.findById(idCliente);

		if (optional.isEmpty()) {
			throw new IllegalArgumentException("O Cliente não foi encontrado, verifique o ID informado.");
		}

		Cliente cliente = optional.get();

		// excluindo no banco de dados
		clienteRepository.delete(cliente);
	}

	public List<Cliente> consultar() throws Exception {

		// retornando todos as cliente do banco de dados
		return (List<Cliente>) clienteRepository.findAll();
	}

	public void consultarId(Integer idCliente) throws Exception {

		// procurando o cliente no banco
		Optional<Cliente> optional = clienteRepository.findById(idCliente);
		if (optional.isEmpty()) {

			throw new IllegalArgumentException("O Cliente não foi encontrado, verifique o ID informado.");
		}
		

	}
}
