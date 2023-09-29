package com.desafio.joao.controller;

import com.desafio.joao.entity.Cargo;
import com.desafio.joao.entity.Setor;
import com.desafio.joao.exceptions.SetorNomesIguais;
import com.desafio.joao.exceptions.SetorNull;
import com.desafio.joao.exceptions.SetorSemId;
import com.desafio.joao.service.SetorService;
import com.desafio.joao.service.TrabalhadorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/setores", consumes = "application/json")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private TrabalhadorService trabalhadorService;

    @PostMapping
    @ApiOperation("Cadastro de setor.")
    public ResponseEntity<?> criarSetor(@RequestBody Setor newSetor) {
        try {
            Setor novoSetor = setorService.cadastrarSetor(newSetor);
            return new ResponseEntity<>(novoSetor, HttpStatus.CREATED);
        } catch (SetorNomesIguais e) {
            return new ResponseEntity<>("Setor com mesmo nome já cadastrado.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualizar Setor.")
    public ResponseEntity<?> atualizarSetor(@PathVariable Integer id, @RequestBody Setor setorAtualizado) {
        try {
            Setor setorCriado = setorService.getPorId(id);
            if (setorCriado == null) {
                return ResponseEntity.notFound().build();
            }
            setorCriado.setNome(setorAtualizado.getNome());
            if (setorAtualizado.getCargo() != null) {
                for (Cargo cargoAtualizado : setorAtualizado.getCargo()) {
                    cargoAtualizado.setSetor(setorCriado);
                    setorCriado.getCargo().add(cargoAtualizado);
                }
            }
            Setor setorAtualizar = setorService.salvarSetor(setorCriado);
            return ResponseEntity.ok(setorAtualizar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/getTodosSetores")
    @ApiOperation(value = "Lista todos setores.")
    public ResponseEntity<?> findSetores() throws SetorNull {
        try {
            List<Setor> findSetores = setorService.buscarSetores();
            if (!findSetores.isEmpty()) {
                return new ResponseEntity<>(findSetores, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (SetorNull e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/buscar/{id}")
    @ApiOperation(value = "Find setor por id.")
    public ResponseEntity<?> findSetorPorId(@PathVariable Integer id) {
        try {
            Setor idSetor = setorService.getPorId(id);
            if (idSetor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(idSetor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value = "Remover Setores.")
    public ResponseEntity<?> deletarSetor(@PathVariable Integer id) throws SetorSemId {
        try {
            Setor setorCriado = setorService.getPorId(id);
            if (setorCriado == null) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        setorService.deletaSetor(id);
        return ResponseEntity.noContent().build();
    }
}
