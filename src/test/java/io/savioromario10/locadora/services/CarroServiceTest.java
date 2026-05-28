package io.savioromario10.locadora.services;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

  
}