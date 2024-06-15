package com.unir.actividad.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.actividad.entities.Persona;
import com.unir.actividad.repositories.PersonaRepository;

@Service
public class PersonaService {

	PersonaRepository repository;

	@Autowired
	public void setRepository(PersonaRepository repository) {
		this.repository = repository;
	}
	
	public List<Persona> findAllP(){
		List<Persona> personas = new ArrayList<Persona>();
		personas.addAll((List<Persona>)repository.findAll());
		return personas;
	}
	
	public Persona saveP(Persona objeto) {
		return repository.save(objeto);
	}
	
	public void deleteP(Integer idpersona) {
		repository.deleteById(idpersona);
	}
	
	public Boolean existeId(Integer pId) {
		boolean result = false;
		if(repository.existsById(pId)) {
			result = true;
		}
		return result;
	}
	
	public Persona findByIdP(Integer pId) {
		return repository.findById(pId).get();
	}
	
	public Persona findByNombreP(String nombre) {
		return repository.findByNombre1(nombre);
	}

}
