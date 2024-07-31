package com.exercise.apirest.service.impl;

import com.exercise.apirest.dto.ClienteDTO;
import com.exercise.apirest.exception.ResourceNotFoundException;
import com.exercise.apirest.model.Cliente;
import com.exercise.apirest.repository.ClienteRepository;
import com.exercise.apirest.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> obtenerClientes() {

        List<Cliente> clienteList = clienteRepository.findAll();

        if (clienteList.isEmpty()) {
            throw new ResourceNotFoundException("No se encuentran clientes", 460);
        }

        return clienteList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO obtenerClientePorId(String id) {
        Cliente cliente = findClienteById(id);
        ClienteDTO clienteDTO = mapToDTO(cliente);
        return clienteDTO;
    }

    @Override
    @Transactional
    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);

        if (!clienteRepository.findByClienteId(cliente.getClienteId()).isEmpty()) {
            throw new ResourceNotFoundException("El cliente ya existe con este ID no se puede crear uno", 460);
        }


        cliente = clienteRepository.save(cliente);
        return mapToDTO(cliente);
    }

    @Override
    public void eliminarCliente(String id) {
        Cliente cliente = findClienteById(id);
        clienteRepository.deleteById(cliente.getClienteId());
    }

    @Override
    @Transactional
    public ClienteDTO actualizarClientePorId(String clientId, ClienteDTO clienteDTO) {

        Cliente clienteExistente = findClienteById(clientId);
        clienteExistente.setNombre(clienteDTO.getNombre());
        clienteExistente.setGenero(clienteDTO.getGenero());
        clienteExistente.setEdad(clienteDTO.getEdad());
        clienteExistente.setIdentificacion(clienteDTO.getIdentificacion());
        clienteExistente.setDireccion(clienteDTO.getDireccion());
        clienteExistente.setTelefono(clienteDTO.getTelefono());
        clienteExistente.setContrasena(clienteDTO.getContrasena());
        clienteExistente.setEstado(clienteDTO.getEstado());
        clienteRepository.save(clienteExistente);

        return mapToDTO(clienteExistente);

    }

    private Cliente findClienteById(String id) {
        return clienteRepository.findByIdentificacion(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado", 461));
    }

    private ClienteDTO mapToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setGenero(cliente.getGenero());
        clienteDTO.setEdad(cliente.getEdad());
        clienteDTO.setIdentificacion(cliente.getIdentificacion());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setClienteId(cliente.getClienteId());
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setEstado(cliente.isEstado());
        return clienteDTO;
    }

    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setClienteId(clienteDTO.getClienteId());
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setEstado(clienteDTO.getEstado());
        return cliente;
    }
}
