package br.com.jhonnbass.dtos;

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
public class ClientePutDto {
	private Integer idCliente;
	private String nome;
	private String email;
	private String cpf;
}
