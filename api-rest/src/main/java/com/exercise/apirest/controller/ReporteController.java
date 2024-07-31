package com.exercise.apirest.controller;

import com.exercise.apirest.dto.ReporteDTO;
import com.exercise.apirest.service.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@AllArgsConstructor
public class ReporteController {

        private final ReporteService reporteService;

        @GetMapping
        public ResponseEntity<?> generarReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("clienteId") Long clienteId) {

            List<ReporteDTO> cuentasConMovimientos = reporteService.generarReporte(fechaInicio, fechaFin, clienteId);

            // Retorna el reporte como respuesta JSON
            return ResponseEntity.ok(cuentasConMovimientos);
        }

}
