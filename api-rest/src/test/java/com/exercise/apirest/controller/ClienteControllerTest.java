package com.exercise.apirest.controller;

import com.exercise.apirest.dto.ClienteDTO;
import com.exercise.apirest.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetClienteById() {
        // Datos de ejemplo
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificacion("123456789");
        clienteDTO.setNombre("Juan Pérez");
        clienteDTO.setGenero("Masculino");
        clienteDTO.setEdad(30);
        clienteDTO.setDireccion("Calle Falsa 123");
        clienteDTO.setTelefono("555-1234");

        // Configurar el mock
        when(clienteService.obtenerClientePorId(anyString())).thenReturn(clienteDTO);

        // Llamar al método del controlador
        ResponseEntity<ClienteDTO> response = clienteController.obtenerClientePorId("123456789");

        // Verificar la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetClienteByIdNotFound() {
        // Configurar el mock
        when(clienteService.obtenerClientePorId(anyString())).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<ClienteDTO> response = clienteController.obtenerClientePorId("123456789");

        // Verificar la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
