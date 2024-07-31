package com.exercise.apirest.controller;

import com.exercise.apirest.dto.ClienteDTO;
import com.exercise.apirest.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class ClienteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    public void testGetClienteById() throws Exception {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificacion("123456789");
        clienteDTO.setNombre("Juan Pérez");
        clienteDTO.setGenero("Masculino");
        clienteDTO.setEdad(30);
        clienteDTO.setDireccion("Calle Falsa 123");
        clienteDTO.setTelefono("555-1234");
        clienteDTO.setContrasena("password123");
        clienteDTO.setEstado(true);

        String jsonCliente = objectMapper.writeValueAsString(clienteDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(jsonCliente, headers);
        restTemplate.exchange("http://localhost:" + port + "/api/v1/clientes", HttpMethod.POST, request,
                              ClienteDTO.class);

        ResponseEntity<ClienteDTO>
            response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/clientes/123456789",
                                                 ClienteDTO.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        ClienteDTO clienteResponse = response.getBody();
        assertThat(clienteResponse).isNotNull();
        assertThat(clienteResponse.getIdentificacion()).isEqualTo("123456789");
        assertThat(clienteResponse.getNombre()).isEqualTo("Juan Pérez");
    }

}
