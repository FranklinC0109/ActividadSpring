package com.unir.actividad.dtos;

import com.unir.actividad.entities.Persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {
    private Integer idMatricula;
    private Integer tipo;
    private String placa;
    private Persona fKPersona;
}
