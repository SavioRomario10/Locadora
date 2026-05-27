package io.savioromario10.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

  @Test
  @DisplayName("Deve calcular o valor do aluguel corretamente")
  void deveCalcularValorAluguel(){
    Carro carro = new Carro("Sedan", 100.0);
    double total = carro.calcularValorAluguel(3);

    Assertions.assertEquals(300.0, total);
  }
}
