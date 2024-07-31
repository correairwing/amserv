package com.manauaradiesel.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manauaradiesel.gestao.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
