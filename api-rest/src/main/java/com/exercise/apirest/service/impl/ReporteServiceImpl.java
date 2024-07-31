package com.exercise.apirest.service.impl;

import com.exercise.apirest.dto.MovimientoDTO;
import com.exercise.apirest.dto.ReporteDTO;
import com.exercise.apirest.model.Cuenta;
import com.exercise.apirest.model.Movimiento;
import com.exercise.apirest.repository.CuentaRepository;
import com.exercise.apirest.repository.MovimientoRepository;
import com.exercise.apirest.service.MovimientoService;
import com.exercise.apirest.service.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final MovimientoRepository movimientoRepository;

    private final CuentaRepository cuentaRepository;

    private final MovimientoService movimientoService;

    @Override
    public List<ReporteDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        // Obtener todas las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        // Lista para almacenar el reporte final
        List<ReporteDTO> cuentasConMovimientos = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {

            List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorCuenta(cuenta.getNumeroCuenta(),
                                                                                       fechaInicio, fechaFin);
            // Obtener los movimientos dentro del rango de fechas para cada cuenta
            //List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween();

            // Construir el DTO de cuenta con movimientos
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            reporteDTO.setSaldoActual(cuenta.getSaldoInicial());
            reporteDTO.setNombreCliente(cuenta.getCliente().getNombre());
            reporteDTO.setMovimientosDtoList(mapToMovimientosDTO(movimientos));

            // Agregar el DTO de cuenta al reporte
            cuentasConMovimientos.add(reporteDTO);
        }

        return cuentasConMovimientos;
    }

    private List<MovimientoDTO> mapToMovimientosDTO(List<Movimiento> movimientos) {

        if (movimientos == null) {
            return Collections.emptyList();
        }

        return movimientos.stream().map(data-> {
            MovimientoDTO movimientoDTO = new MovimientoDTO();
            movimientoDTO.setId(data.getId());
            movimientoDTO.setNumeroCuenta(data.getCuenta().getNumeroCuenta());
            movimientoDTO.setTipoMovimiento(data.getTipoMovimiento());
            movimientoDTO.setValor(data.getValor());
            movimientoDTO.setSaldo(data.getSaldo());
            movimientoDTO.setFecha(data.getFecha());
            return movimientoDTO;
        }).collect(Collectors.toUnmodifiableList());
    }
}
