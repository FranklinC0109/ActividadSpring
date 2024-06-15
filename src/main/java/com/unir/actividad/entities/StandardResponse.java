package com.unir.actividad.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardResponse<T> {
	
	private List<T> objetos;
	private T objeto;
	private String estado;
	private String mensaje;
	
	
	public StandardResponse(String estado) {
		this.estado = estado;
	}

	public StandardResponse(String estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	
	
}
