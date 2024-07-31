package com.exercise.apirest.service;

import com.exercise.apirest.dto.MovimientoDTO;
import com.exercise.apirest.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    List<MovimientoDTO> obtenerMovimientos();

    Optional<MovimientoDTO> obtenerMovimientoPorId(Long id);

    MovimientoDTO registrarMovimiento(MovimientoDTO movimientoDTO);

    List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta, LocalDate fechaInicio,
        LocalDate fechaFin);
}
