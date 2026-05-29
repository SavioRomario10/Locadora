package io.savioromario10.locadora.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carros")
public class CarroEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String marca;
  @Column
  private String modelo;
  @Column
  private double valorDiaria;
  @Column
  private int ano;

  @Deprecated
  public CarroEntity() {}
  public CarroEntity(String modelo, double valorDiaria, int ano) {
    this.modelo = modelo;
    this.valorDiaria = valorDiaria;
    this.ano = ano;
  }
  public CarroEntity(Long id, String modelo, double valorDiaria, int ano) {
    this(modelo, valorDiaria, ano);
    this.id = id;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getMarca() {
    return marca;
  }
  public void setMarca(String marca) {
    this.marca = marca;
  }
  public String getModelo() {
    return modelo;
  }
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }
  public double getValorDiaria() {
    return valorDiaria;
  }
  public void setValorDiaria(double valorDiaria) {
    this.valorDiaria = valorDiaria;
  }
  public int getAno() {
    return ano;
  }
  public void setAno(int ano) {
    this.ano = ano;
  }
}