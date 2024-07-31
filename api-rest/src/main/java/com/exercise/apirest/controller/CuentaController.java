package com.exercise.apirest.controller;

import com.exercise.apirest.dto.CuentaDTO;
import com.exercise.apirest.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> obtenerListaCuentas() {
        return new ResponseEntity<>(cuentaService.obtenerCuentas(), HttpStatus.OK);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorNumero(@PathVariable String numeroCuenta) {
        Optional<CuentaDTO> cuentaDTO = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
        return cuentaDTO.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(cuentaDTO));
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@PathVariable Long numeroCuenta, @RequestBody CuentaDTO cuentaActualizada) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(numeroCuenta, cuentaActualizada));
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long numeroCuenta) {
        cuentaService.eliminarCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}
