package io.savioromario10.locadora.model;

import io.savioromario10.locadora.model.exception.ReservaInvalidaException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
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

    var reserva = assertDoesNotThrow(() -> new Reserva(carro, cliente, dias));

    assertThat(reserva).isNotNull();
  }

  @Test
  @DisplayName("Deve dar erro ao criar uma reserva com dias negativos")
  void deveDarErroAoCriarUmaReservaComDiasNegativos(){

    assertThrows(ReservaInvalidaException.class, () -> new Reserva(carro, cliente, 0));
    
    assertThat(catchThrowable(() -> new Reserva(carro, cliente, -1)))
        .isInstanceOf(ReservaInvalidaException.class)
        .hasMessage("A quantidade de dias deve ser maior que zero.");

  }
  @Test
  @DisplayName("Deve calcular o total da reserva")
  void deveCalcularOTotalDaReserva(){

    var reserva = assertDoesNotThrow(() -> new Reserva(carro, cliente, 3));

    var total = reserva.calcularTotal();

    assertThat(total).isEqualTo(300.0);
  }
}
