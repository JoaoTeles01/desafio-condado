package com.desafio.joao.controller;

import com.desafio.joao.entity.Cargo;
import com.desafio.joao.exceptions.MesmoNomeCargo;
import com.desafio.joao.service.CargoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping
    @ApiOperation(value = "Cadastro de Cargos.")
    public ResponseEntity<?> cadastrarCargos(@RequestBody Cargo newCargo){
        try{
            Cargo cargo = cargoService.cadastrarCargo(newCargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (MesmoNomeCargo e){
            return new ResponseEntity<>("Cargo com nome ja existente!", HttpStatus.BAD_REQUEST);
        }
    }
}
