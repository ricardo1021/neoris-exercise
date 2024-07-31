package com.exercise.apirest.repository;

import com.exercise.apirest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String id);

    Optional<Cliente> findByClienteId(Long id);
}
