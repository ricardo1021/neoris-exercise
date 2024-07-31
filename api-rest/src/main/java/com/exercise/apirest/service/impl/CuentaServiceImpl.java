package com.exercise.apirest.service.impl;

import com.exercise.apirest.dto.CuentaDTO;
import com.exercise.apirest.exception.ResourceNotFoundException;
import com.exercise.apirest.model.Cliente;
import com.exercise.apirest.model.Cuenta;
import com.exercise.apirest.repository.ClienteRepository;
import com.exercise.apirest.repository.CuentaRepository;
import com.exercise.apirest.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {


    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public List<CuentaDTO> obtenerCuentas() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();

        if (cuentaList.isEmpty()) {
            throw new ResourceNotFoundException("No se encuentran cuentas registradas", 460);
        }

        return cuentaList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CuentaDTO> obtenerCuentaPorNumero(String numeroCuenta) {
        Cuenta cuenta = obtenerCuentaNumero(numeroCuenta);
        return Optional.ofNullable(mapToDTO(cuenta));
    }

    private Cuenta obtenerCuentaNumero(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(()-> new ResourceNotFoundException("No se encuentra la cuenta solicitada.", 461));
    }

    private Cuenta obtenerCuentaId(Long numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta.toString())
            .orElseThrow(()-> new ResourceNotFoundException("No se encuentra la cuenta solicitada.", 461));
    }

    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {

        Optional<Cliente> cliente = clienteRepository.findByIdentificacion(cuentaDTO.getClienteIdentificacion());
        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("El cliente No existe con este ID no se puede registrar una cuenta",
                                                460);
        }
        Cuenta cuenta = mapToEntity(cliente.get(), cuentaDTO);
        cuenta = cuentaRepository.save(cuenta);
        return mapToDTO(cuenta);
    }

    @Override
    public CuentaDTO actualizarCuenta(Long numeroCuenta, CuentaDTO cuentaActualizada) {
        Cuenta cuentaExistente = obtenerCuentaId(numeroCuenta);

        cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuentaExistente.setEstado(cuentaActualizada.getEstado());
        cuentaExistente = cuentaRepository.save(cuentaExistente);

        return mapToDTO(cuentaExistente);
    }

    @Override
    public void eliminarCuenta(Long numeroCuenta) {
        Cuenta cuenta = obtenerCuentaId(numeroCuenta);
        cuentaRepository.delete(cuenta);
    }

    private CuentaDTO mapToDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.isEstado());
        cuentaDTO.setClienteIdentificacion(cuenta.getCliente().getIdentificacion());
        cuentaDTO.setNombreCliente(cuenta.getCliente().getNombre());
        return cuentaDTO;
    }

    private Cuenta mapToEntity(Cliente cliente, CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuenta.setCliente(cliente);
        return cuenta;
    }
}
