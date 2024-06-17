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

	/**
	 * Método que inyecta la dependica repositorio persona
	 * @param PersonaRepository repository
	 * */
	@Autowired
	public void setRepository(PersonaRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Método que busca todos los registros de la clase persona
	 * @return List<Persona>
	 * */
	public List<Persona> findAllP(){
		List<Persona> personas = new ArrayList<Persona>();
		personas.addAll((List<Persona>)repository.findAll());
		return personas;
	}
	
	/**
	 * Método que crear un objeto de la clase persona
	 * @param Persona objeto
	 * @return Persona
	 */
	public Persona saveP(Persona objeto) {
		return repository.save(objeto);
	}
	
	/**
	 * Método que elimina un objeto de la clase persona
	 * @param Integer idpersona
	 */
	public void deleteP(Integer idpersona) {
		repository.deleteById(idpersona);
	}
	
	/**
	 * Método que busca por Id si existe el registro
	 * @param Integer pId
	 * @return Boolean
	 * */
	public Boolean existeId(Integer pId) {
		boolean result = false;
		if(repository.existsById(pId)) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Método que obtiene un registro por el ID
	 * @param Integer pId
	 * @return Persona
	 */
	public Persona findByIdP(Integer pId) {
		return repository.findById(pId).get();
	}
	
	/**
	 * Método que obtiene un registro por el Nombre
	 * @param String nombre
	 * @return Persona
	 */
	public Persona findByNombreP(String nombre) {
		return repository.findByNombre1(nombre);
	}

}
