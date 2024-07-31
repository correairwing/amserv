package com.manauaradiesel.gestao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manauaradiesel.gestao.entity.Cliente;
import com.manauaradiesel.gestao.entity.StatusVeiculo;
import com.manauaradiesel.gestao.entity.Veiculo;
import com.manauaradiesel.gestao.exceptions.ResourceNotFoundException;
import com.manauaradiesel.gestao.repository.ClienteRepository;
import com.manauaradiesel.gestao.repository.VeiculoRepository;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> findById(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo save(Veiculo veiculo) {
        if (veiculo.getCliente().getId() != null) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(veiculo.getCliente().getId());
            if (!clienteOptional.isPresent()) {
                throw new ResourceNotFoundException("Cliente not found with id " + veiculo.getCliente().getId());
            }
            veiculo.setCliente(clienteOptional.get());
        }
        if (veiculo.getStatus() == null) {
            veiculo.setStatus(StatusVeiculo.PENDENTE); // Defina um status padrão se necessário
        }
        return veiculoRepository.save(veiculo);
    }

    public void deleteById(Long id) {
        veiculoRepository.deleteById(id);
    }
}