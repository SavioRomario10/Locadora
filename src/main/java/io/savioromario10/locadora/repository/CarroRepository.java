package io.savioromario10.locadora.repository;

import java.util.List;

import io.savioromario10.locadora.entity.CarroEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

  List<CarroEntity> findByModelo(String modelo);
}