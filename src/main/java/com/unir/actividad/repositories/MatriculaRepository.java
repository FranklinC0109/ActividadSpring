package com.unir.actividad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unir.actividad.entities.Matricula;

public interface MatriculaRepository extends CrudRepository<Matricula, Integer>{
    
    @Query(value = "select * from matriculas where fK_Persona = :busqueda", nativeQuery = true)
    public List<Matricula> consultarPorPersona (@Param("busqueda") Integer busqueda);

    public Matricula findByPlaca(String placa);

}
