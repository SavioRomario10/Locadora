package io.savioromario10.locadora.model;

public class Carro {

  private String modelo;
  private double valorDiaria;
  
  public Carro() {}
  public Carro(String modelo, double valorDiaria) {
    this.modelo = modelo;
    this.valorDiaria = valorDiaria;
  }

  public double calcularValorAluguel(int dias) {
    return valorDiaria * dias;
  }

  public String getModelo() {return modelo;}
  public void setModelo(String modelo) {this.modelo = modelo;}
  public double getValorDiaria() {return valorDiaria;}
  public void setValorDiaria(double valorDiaria) {this.valorDiaria = valorDiaria;}
}
