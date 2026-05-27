package io.savioromario10.locadora.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

  @Test
  @DisplayName("Deve criar cliente com nome")
  void deveCriarClientComNome(){
    Cliente cliente = new Cliente("João");

    String nome = cliente.getNome();

    assertEquals("João", nome);
    assertNotNull(nome);
    assertTrue(cliente.getNome().startsWith("J"));
    
    assertThat(nome).isNotEmpty().startsWith("J").endsWith("o").contains("ã");
  }
}
