package br.com.jhonnbass.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.jhonnbass.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {	
	
	@Query("select u from Cliente u where u.email = :param")
	Cliente findByEmail(@Param("param") String email) throws Exception;
	
	
	@Query("select u from Cliente u where u.cpf = :param")
	Cliente findByCpf(@Param("param") String cpf) throws Exception;
	
	//@Query("select u from Cliente u where u.idCliente = :param1")
///	Cliente findByIdCliente(@Param("param1") Integer idCliente) throws Exception;
}
