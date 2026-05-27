package io.savioromario10.locadora.model;

import io.savioromario10.locadora.model.exception.ReservaInvalidaException;

public class Reserva {

  private Carro carro;
  private Cliente cliente;
  private int dias;

  public Reserva(){}
  public Reserva(Carro carro, Cliente cliente, int dias) {
    
    if(dias <= 0){
      throw new ReservaInvalidaException("A quantidade de dias deve ser maior que zero.");
    }
    
    this.carro = carro;
    this.cliente = cliente;
    this.dias = dias;
  }

  public double calcularTotal(){
    return this.carro.getValorDiaria() * this.dias;
  }
}
