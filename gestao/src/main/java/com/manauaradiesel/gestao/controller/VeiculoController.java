package com.manauaradiesel.gestao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manauaradiesel.gestao.entity.Veiculo;
import com.manauaradiesel.gestao.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.findById(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.save(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetails) {
        Optional<Veiculo> veiculoOptional = veiculoService.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            veiculo.setMarca(veiculoDetails.getMarca());
            veiculo.setModelo(veiculoDetails.getModelo());
            veiculo.setAno(veiculoDetails.getAno());
            veiculo.setPlaca(veiculoDetails.getPlaca());
            veiculo.setCliente(veiculoDetails.getCliente());
            veiculo.setStatus(veiculoDetails.getStatus());
            veiculo.setObservacoes(veiculoDetails.getObservacoes());
            return ResponseEntity.ok(veiculoService.save(veiculo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

