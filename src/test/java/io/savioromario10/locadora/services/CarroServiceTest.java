package io.savioromario10.locadora.services;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import io.savioromario10.locadora.entity.CarroEntity;
import io.savioromario10.locadora.model.exception.EntityNotFoundException;
import io.savioromario10.locadora.repository.CarroRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

  @InjectMocks
  CarroService carroService;

  @Mock
  CarroRepository carroRepository;

  @Test
  void deveSalvarUmCarro(){

    CarroEntity carro = new CarroEntity("Sedan", 10.0, 2010);
    carro.setId(1L);

    when(carroRepository.save(any(CarroEntity.class))).thenReturn(carro);

    var carroSalvo = carroService.salvar(carro);

    assertNotNull(carroSalvo);
    assertEquals(carro.getModelo(), carroSalvo.getModelo());

    verify(carroRepository).save(any(CarroEntity.class));
  }

  @Test
  void deveDarErroPrecoNegativo(){

    CarroEntity carro = new CarroEntity("Sedan", -10.0, 2010);
    carro.setId(1L);

    assertThrows(IllegalArgumentException.class, () -> carroService.salvar(carro));

    verify(carroRepository, never()).save(any(CarroEntity.class));
  }

  @Test
  void deveAtualizarUmCarro(){

    var carro = new CarroEntity("Gol", 10.0, 2010);
    when(carroRepository.findById(1L)).thenReturn(Optional.of(carro));

    var carroAtualizado = new CarroEntity("Gol", 10.0, 2010);
    when(carroRepository.save(any(CarroEntity.class))).thenReturn(carroAtualizado);

    Long id = 1L;
    CarroEntity carroExistente = new CarroEntity("Sedan", 10.0, 2010);

    var carroResultado = carroService.atualizar(id, carroExistente);

    assertEquals(carroAtualizado.getModelo(), carroResultado.getModelo());
    verify(carroRepository).save(Mockito.any());
  }

  @Test
  void deveDarErroAoAtualizarCarroInesistente(){
    Long id = 1L;
    CarroEntity carroExistente = new CarroEntity("Sedan", 10.0, 2010);

    when(carroRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> carroService.atualizar(id, carroExistente));
    verify(carroRepository, never()).save(any(CarroEntity.class));
  }

  @Test
  void deveDarErroAoDeletarCarroInesistente(){
    Long id = 1L;

    when(carroRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> carroService.deletar(id));
    verify(carroRepository, never()).delete(any(CarroEntity.class));
  }

  @Test
  void deveDeletarCarroExistente(){
    Long id = 1L;
    CarroEntity carroExistente = new CarroEntity("Sedan", 10.0, 2010);

    when(carroRepository.findById(id)).thenReturn(Optional.of(carroExistente));

    carroService.deletar(id);
    
    verify(carroRepository, times(1)).delete(carroExistente);
  }

  @Test
  void deveBuscarCarroPorId(){
    Long id = 1L;
    CarroEntity carroExistente = new CarroEntity("Sedan", 10.0, 2010);

    when(carroRepository.findById(any())).thenReturn(Optional.of(carroExistente));

    var carroEncontrado = carroService.buscarPorId(id);

    assertThat(carroEncontrado.getModelo()).isEqualTo(carroExistente.getModelo());
  }

  @Test
  void deveListarTodos(){
    var carro = new CarroEntity(1, "Sedan", 10.0, 2020);
    var carro2 = new CarroEntity(1, "Sedan", 10.0, 2020);

    var lista = List.of(carro, carro2);

    when(carroRepository.findAll()).thenReturn(lista);

    var carros = carroService.listarTodos();

    assertThat(carros.size()).isEqualTo(2);
    verify(carroRepository, times(1)).findAll();
    verifyNoMoreInteractions(carroRepository);
  }
}