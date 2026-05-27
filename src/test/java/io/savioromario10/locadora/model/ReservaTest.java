package io.savioromario10.locadora.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class ReservaTest {

  Cliente cliente;
  Carro carro;

  @BeforeEach
  void setup(){
    cliente = new Cliente("João");
    carro = new Carro("Fusca", 100.0);
  }
  
  @Test
  @DisplayName("Deve criar uma reserva")
  void deveCriarUmaReserva(){

    var dias = 5;

    var reserva = new Reserva(carro, cliente, dias);

    assertThat(reserva).isNotNull();
  }

  @Test
  @DisplayName("Deve dar erro ao criar uma reserva com dias negativos")
  void deveDarErroAoCriarUmaReservaComDiasNegativos(){

  }
  @Test
  @DisplayName("Deve calcular o total da reserva")
  void deveCalcularOTotalDaReserva(){

  }
}
