package io.savioromario10.locadora.controller;

import io.savioromario10.locadora.entity.CarroEntity;
import io.savioromario10.locadora.model.exception.EntityNotFoundException;
import io.savioromario10.locadora.services.CarroService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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

  @Test
  void deveListarCarros() throws Exception {
    var listagem = List.of(
      new CarroEntity(1L,"Sedan", 10.0, 2010),
      new CarroEntity(2L,"SUV", 50.0, 2019)
    );

    when(service.listarTodos()).thenReturn(listagem);

    mvc.perform(get("/carros"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").value(1L))
      .andExpect(jsonPath("$[0].modelo").value("Sedan"))
      .andExpect(jsonPath("$[0].valorDiaria").value(10.0))
      .andExpect(jsonPath("$[0].ano").value(2010))
      .andExpect(jsonPath("$[1].id").value(2L))
      .andExpect(jsonPath("$[1].modelo").value("SUV"))
      .andExpect(jsonPath("$[1].valorDiaria").value(50.0))
      .andExpect(jsonPath("$[1].ano").value(2019));
  }

  @Test
  void deveAtualizarUmCarro() throws Exception{
    when(service.atualizar(any(), any())).thenReturn( new CarroEntity(1L, "SUV", 150.0, 2023));

    String json =  """
      {
        "modelo": "Sedan",
        "valorDiaria": 150.0,
        "ano": 2027
      }
      """;

    mvc.perform(put("/carros/1")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json)
    );
  }

  @Test
  void deveDarErroAtualizarCarro() throws Exception{
    when(service.atualizar(any(), any())).thenThrow(EntityNotFoundException.class);

    String json =  """
      {
        "modelo": "Sedan",
        "valorDiaria": 150.0,
        "ano": 2027
      }
      """;

    mvc.perform(
      put("/carros/1")
        .contentType("application/json")
        .content(json)
      ).andExpect(status().isNotFound());
  }
}