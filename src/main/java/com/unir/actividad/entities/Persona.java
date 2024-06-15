package com.unir.actividad.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Persona", nullable = false)
	private Integer id;
	@Column(name="nombre1", length = 50, nullable = false)
	private String nombre1;
	@Column(name="nombre2", length = 50)
	private String nombre2;
	@Column(name="apellido1", length = 50, nullable = false)
	private String apellido1;
	@Column(name="apellido2", length = 50)
	private String apellido2;
	
}
