package com.desafio.joao.service;

import com.desafio.joao.entity.Trabalhador;
import com.desafio.joao.exceptions.FunciCpfsIguais;
import com.desafio.joao.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TrabalhadorService {

    @Autowired
    private TrabalhadorRepository repoTrabalhador;

    public Trabalhador cadastrarTrabalhador(@RequestBody Trabalhador novoTrabalhador) throws FunciCpfsIguais {
        Trabalhador trabalhadorComMesmoCpf = repoTrabalhador.getByCpf(novoTrabalhador.getCpf());
        if (trabalhadorComMesmoCpf != null && trabalhadorComMesmoCpf.getCpf() == novoTrabalhador.getCpf()) {
            throw new FunciCpfsIguais(HttpStatus.BAD_REQUEST);
        }
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setCargo(novoTrabalhador.getCargo());
        trabalhador.setName(novoTrabalhador.getName());
        trabalhador.setCpf(novoTrabalhador.getCpf());
        trabalhador.setSetor(novoTrabalhador.getSetor());
        return repoTrabalhador.save(trabalhador);
    }
}
