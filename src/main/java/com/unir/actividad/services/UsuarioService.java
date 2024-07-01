package com.unir.actividad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.actividad.entities.Usuario;
import com.unir.actividad.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    UsuarioRepository repository;

	@Autowired
	public void setRepository(UsuarioRepository repository) {
		this.repository = repository;
	}

    public Usuario consultarIngreso(String usu,String contra) {
		return repository.consultarIngreso(usu,contra);
	}
}
