package com.exercise.apirest.controller;

import com.exercise.apirest.dto.ClienteDTO;
import com.exercise.apirest.service.ClienteService;
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

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return new ResponseEntity<>(clienteService.obtenerClientes(), HttpStatus.OK);
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable String clienteId) {
        return new ResponseEntity<>(clienteService.obtenerClientePorId(clienteId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.registrarCliente(clienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable String id, @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.actualizarClientePorId(id, clienteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCliente(@PathVariable String id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
