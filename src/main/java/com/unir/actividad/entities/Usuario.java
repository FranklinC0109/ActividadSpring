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
@Table(name="usuario")
public class Usuario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUsuario", nullable = false)
	private Integer id;
	@Column(name="Nombres", length = 45, nullable = false)
	private String nombres;
	@Column(name="Apellidos", length = 45, nullable = false)
	private String apellidos;
	@Column(name="Usuario", length = 45, nullable = false)
	private String usuario;
	@Column(name="Contraseña", length = 45, nullable = false)
	private String contraseña;
}
