package com.unir.actividad.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unir.actividad.entities.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	
	/**
	 * FDCM: Método que consulta por el primer nombre
	 * */
	public Persona findByNombre1(String nombre);
	
	/**
	 * FDCM: Método que consulta por la cédula de la persona
	 * */
	public Persona findByCedula(Integer cedula);

}
