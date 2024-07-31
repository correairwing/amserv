package com.manauaradiesel.gestao.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String marca;
    private String modelo;
    private Integer ano;
    private String placa;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Transient
    private Long clienteId;
    
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;
    
    @ElementCollection
    private List<String> observacoes = new ArrayList<>();
}
