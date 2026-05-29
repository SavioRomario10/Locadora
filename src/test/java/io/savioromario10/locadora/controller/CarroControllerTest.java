package io.savioromario10.locadora.controller;

import io.savioromario10.locadora.entity.CarroEntity;
import io.savioromario10.locadora.model.exception.EntityNotFoundException;
import io.savioromario10.locadora.services.CarroService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  CarroService service;

  @Test 
  void deveSalvarCarro() throws Exception {
    CarroEntity carro = 
        new CarroEntity(1L,"Sedan", 10.0, 2010);

    when(service.salvar(any())).thenReturn(carro);

    String json = """
      {
        "modelo": "Sedan",
        "valorDiaria": 150.0,
        "ano": 2027
      }
      """;

    ResultActions result = mvc.perform(
      post("/carros")
        .contentType("application/json")
        .content(json)
    );

    result
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value(1L))
      .andExpect(jsonPath("$.modelo").value("Sedan"))
      .andExpect(jsonPath("$.valorDiaria").value(10.0))
      .andExpect(jsonPath("$.ano").value(2010));
  }

  @Test
  void deveBuscarCarroPorId() throws Exception {
    when(service.buscarPorId(any())).thenReturn(
      new CarroEntity(1L,"Sedan", 10.0, 2010)
    );

    mvc.perform(
      get("/carros/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.modelo").value("Sedan"))
        .andExpect(jsonPath("$.valorDiaria").value(10.0))
        .andExpect(jsonPath("$.ano").value(2010));
  }

  @Test
  void deveDarErroBuscarCarroPorId() throws Exception {
    when(service.buscarPorId(any())).thenThrow(EntityNotFoundException.class);

    mvc.perform(
      get("/carros/1"))
        .andExpect(status().isNotFound());
  }
}