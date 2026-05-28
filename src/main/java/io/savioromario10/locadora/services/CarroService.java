package io.savioromario10.locadora.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.savioromario10.locadora.repository.CarroRepository;
import io.savioromario10.locadora.entity.CarroEntity;
import io.savioromario10.locadora.model.exception.EntityNotFoundException;

@Service
public class CarroService {

  @Autowired
  private CarroRepository carroRepository;

  public CarroService(CarroRepository carroRepository) {
    this.carroRepository = carroRepository;
  }

  public CarroEntity salvar(CarroEntity carro) {
    if(carro.getValorDiaria() <= 0) {
      throw new IllegalArgumentException("O valor da diária deve ser maior que zero.");
    }

    return carroRepository.save(carro);
  }

  public CarroEntity atualizar(Long id, CarroEntity carroAtualizado){
    var carroExistente = carroRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));

    carroExistente.setModelo(carroAtualizado.getModelo());
    carroExistente.setMarca(carroAtualizado.getMarca());
    carroExistente.setAno(carroAtualizado.getAno());
    carroExistente.setValorDiaria(carroAtualizado.getValorDiaria());

    return carroRepository.save(carroExistente);
  }

  public void deletar(Long id){
    var carroExistente = carroRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));

    carroRepository.deleteById(carroExistente.getId());
  }

  public CarroEntity buscarPorId(Long id){
    return carroRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Carro com ID " + id + " não encontrado."));
  }

  public List<CarroEntity> listarTodos(){
    return carroRepository.findAll();
  }
}