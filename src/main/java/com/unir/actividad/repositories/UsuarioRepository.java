package com.unir.actividad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unir.actividad.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    @Query(value = "select * from usuario where Usuario = :usu and Contraseña = :contra", nativeQuery = true)
    public Usuario consultarIngreso (@Param("usu") String usu, @Param("contra") String contra);
}
