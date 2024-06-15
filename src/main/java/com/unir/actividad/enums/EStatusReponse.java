package com.unir.actividad.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStatusReponse {

	SUCCESS(1, "SUCCESS"),
	ERROR(0, "ERROR");
	
	private int id;
	private String nombre;
	
}
