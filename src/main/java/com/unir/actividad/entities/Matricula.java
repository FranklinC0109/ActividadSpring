package com.unir.actividad.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matriculas")
public class Matricula {
    
    @Id
    @Column(name = "id_Matricula",nullable = false)
    private Integer idMatricula;

    /**
     * 1-carro
     * 2-moto
     */
    @Column(name = "Tipo",nullable = false)
    private Integer tipo;
    
    @Column(name = "Placa", length = 6,nullable = false)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "fK_Persona")
    private Persona fKPersona;

}
