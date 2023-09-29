package com.desafio.joao.exceptions;

import org.springframework.http.HttpStatus;

public class FunciCpfsIguais extends Exception {

    public FunciCpfsIguais(HttpStatus badRequest){
        super();
    }

    public FunciCpfsIguais(String mensagem){
        super(mensagem);
    }
}
