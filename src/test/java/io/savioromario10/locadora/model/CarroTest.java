package io.savioromario10.locadora.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

  @Test
  @DisplayName("Deve calcular o valor do aluguel corretamente")
  void deveCalcularValorAluguel(){
    Carro carro = new Carro("Sedan", 100.0);

    double total = carro.calcularValorAluguel(3);

    assertEquals(300.0, total);
  }

  @Test
  @DisplayName("Deve calcular o valor do aluguel com desconto")
  void deveCalcularAluguelComDesconto(){
    Carro carro = new Carro("Sedan", 100.0);
    int quantDias = 5;

    double total = carro.calcularValorAluguel(quantDias);

    assertEquals(450.0, total);
  }
}
