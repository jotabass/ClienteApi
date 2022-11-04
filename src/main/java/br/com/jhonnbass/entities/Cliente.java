package br.com.jhonnbass.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCLIENTE")
	private Integer idCliente;
	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;
	@Column(name = "CPF", length = 15, nullable = false)
	private String cpf;
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;




}
