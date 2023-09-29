package com.desafio.joao.exceptions;

public class SetorNomesIguais extends Exception {

    public SetorNomesIguais(){
        super();
    }

    public SetorNomesIguais(String mensagem){
        super(mensagem);
    }
}
