package com.unir.actividad.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unir.actividad.entities.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	
	public Persona findByNombre1(String nombre);

}
