package br.com.fiap.adjt.parkmeter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	
	String logradouro;
	Integer numero;
	String bairro;
	String cidade;
	String uf;

}
