package com.exercise.apirest.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Long clienteId;
    private String contrasena;
    private Boolean estado;
}
