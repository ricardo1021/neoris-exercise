package com.exercise.apirest.service;

import com.exercise.apirest.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> obtenerClientes();

    ClienteDTO obtenerClientePorId(String id);

    ClienteDTO registrarCliente(ClienteDTO clienteDTO);

    void eliminarCliente(String id);

    ClienteDTO actualizarClientePorId(String clientId, ClienteDTO clienteDTO);
}
