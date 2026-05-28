package io.savioromario10.locadora.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.savioromario10.locadora.entity.CarroEntity;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

  @Autowired
  CarroRepository repository;

  @Test
  void deveSalvarCarro(){
    var entity = new CarroEntity("Uno", 100.0, 2015);
    repository.save(entity);

    assertNotNull(entity.getId());
  }

  @Deprecated
  @Sql("/sql/popular-carros.sql")
  void deveBuscarCarroPorModelo(){
    List<CarroEntity> carros = repository.findByModelo("SUV");

    assertEquals(1, carros.size());
  }
}