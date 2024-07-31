package com.exercise.apirest.service;

import com.exercise.apirest.dto.ReporteDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {

    List<ReporteDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);

}
