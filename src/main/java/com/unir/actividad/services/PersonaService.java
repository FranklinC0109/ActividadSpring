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
	
	public Boolean deleteP(Integer idpersona) {
		boolean result = false;
		if(idpersona != null) {
			if(repository.existsById(idpersona)) {
				repository.deleteById(idpersona);
				result = true;
			}
		}
		return result;
		
	}

}
