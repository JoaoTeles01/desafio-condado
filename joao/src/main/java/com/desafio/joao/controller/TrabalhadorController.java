package com.desafio.joao.controller;

import com.desafio.joao.entity.Trabalhador;
import com.desafio.joao.exceptions.FunciCpfsIguais;
import com.desafio.joao.service.TrabalhadorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trabalhadores")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorService trabalhadorService;

    @PostMapping
    @ApiOperation(value = "Cadastro de Trabalhador")
    public ResponseEntity<?> cadastroTrabalhador(@RequestBody Trabalhador newTrabalhador) {
        try {
            Trabalhador trabalhador = trabalhadorService.cadastrarTrabalhador(newTrabalhador);
            return new ResponseEntity<>("Trabalhador cadastrado com sucesso.", HttpStatus.CREATED);
        } catch (FunciCpfsIguais e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
