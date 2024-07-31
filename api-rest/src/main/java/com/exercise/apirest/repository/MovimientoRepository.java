package com.exercise.apirest.repository;

import com.exercise.apirest.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdAndFechaBetween(Long id, LocalDate fechaInicio, LocalDate fechaFin);

}
