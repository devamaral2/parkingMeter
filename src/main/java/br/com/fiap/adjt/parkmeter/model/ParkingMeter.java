package br.com.fiap.adjt.parkmeter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingMeter {
	
	@Id
	private String id;
	private Endereco endereco;
}
