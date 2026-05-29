package io.savioromario10.locadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.savioromario10.locadora.entity.CarroEntity;
import io.savioromario10.locadora.model.exception.EntityNotFoundException;
import io.savioromario10.locadora.services.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

  private final CarroService service;

  public CarroController(CarroService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro) {
    try{
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.salvar(carro));
    }catch(IllegalArgumentException e){
      return ResponseEntity
          .status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarPorId( @PathVariable("id") Long id) {
    try{
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.buscarPorId(id));

    }catch(EntityNotFoundException e){
      return ResponseEntity
          .notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<CarroEntity>> listarTodos() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(service.listarTodos());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody CarroEntity carroEntity){
    try{
      service.atualizar(id, carroEntity);
      return ResponseEntity.noContent().build();
    }catch(EntityNotFoundException e){
      return ResponseEntity.notFound().build();
    }
  }
}
