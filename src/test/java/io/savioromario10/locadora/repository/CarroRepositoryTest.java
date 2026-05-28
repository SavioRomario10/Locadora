package io.savioromario10.locadora.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.savioromario10.locadora.entity.CarroEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

  @Autowired
  CarroRepository repository;

  CarroEntity entity;

  @BeforeEach
  void setup(){
    entity = new CarroEntity("Uno", 100.0, 2015);
  }

  @Test
  void deveSalvarCarro(){
    var entity = new CarroEntity("Uno", 100.0, 2015);
    repository.save(entity);

    assertNotNull(entity.getId());
  }

  @Test
  @Sql("/sql/popular-carros.sql")
  void deveBuscarCarroPorModelo(){
    List<CarroEntity> carros = repository.findByModelo("SUV");

    assertEquals(1, carros.size());

    assertThat(carros.get(0).getModelo()).isEqualTo("SUV");
  }

  @Test
  void deveBuscarCarroPorId(){
    var carroSalvo = repository.save(entity);

    Optional<CarroEntity> carro = repository.findById(carroSalvo.getId());

    assertThat(carro).isPresent();
    assertThat(carro.get().getModelo()).isEqualTo("Uno");
  }

  @Test
  void deveAtualizarCarro(){
    
    var carroSalvo = repository.save(entity);

    carroSalvo.setAno(2020);
    var novoCarro = repository.save(carroSalvo);

    assertThat(novoCarro.getAno()).isEqualTo(2020);
  }

  @Test
  void deveDeletarCarro(){
    var carroSalvo = repository.save(entity);

    repository.deleteById(carroSalvo.getId());

    Optional<CarroEntity> carro = repository.findById(carroSalvo.getId());

    assertThat(carro).isNotPresent();
  }
}