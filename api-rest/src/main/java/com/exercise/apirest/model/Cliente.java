package com.exercise.apirest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente extends Persona {
    @Column(unique = true)
    private Long clienteId;
    private String contrasena;
    private boolean estado;

}
