package io.savioromario10.locadora.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import io.savioromario10.locadora.repository.CarroRepository;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

  @InjectMocks
  CarroService carroService;

  @Mock
  CarroRepository carroRepository;

  @Test
  void deveSalvarUmCarro(){
    
  }
}
