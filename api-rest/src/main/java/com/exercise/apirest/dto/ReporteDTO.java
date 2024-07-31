package com.exercise.apirest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReporteDTO {

        private String numeroCuenta;
        private BigDecimal saldoActual;
        private String nombreCliente;
        private List<MovimientoDTO> movimientosDtoList;

}
