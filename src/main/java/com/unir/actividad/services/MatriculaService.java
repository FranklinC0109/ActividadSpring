package com.unir.actividad.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.actividad.entities.Matricula;
import com.unir.actividad.repositories.MatriculaRepository;

@Service
public class MatriculaService {
    MatriculaRepository repository;

    @Autowired
	public void setRepository(MatriculaRepository repository) {
		this.repository = repository;
	}
	
	public List<Matricula> findAllP(){
		List<Matricula> matriculas= new ArrayList<Matricula>();
		matriculas.addAll((List<Matricula>)repository.findAll());
		return matriculas;
	}
	
	public Matricula saveMatricula(Matricula objeto) {
		return repository.save(objeto);
	}
	
	public void deleteMatricula(Integer idMatricula) {
		repository.deleteById(idMatricula);
	}
	
	public Boolean existeIdMatricula(Integer idMatricula) {
		boolean result = false;
		if(repository.existsById(idMatricula)) {
			result = true;
		}
		return result;
	}
	
	public Matricula findByIdMatricula(Integer pId) {
		return repository.findById(pId).get();
	}
}
