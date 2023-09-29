package com.desafio.joao.exceptions;

public class MesmoNomeCargo extends Exception {

    public MesmoNomeCargo(){
        super();
    }

    public MesmoNomeCargo(String mensagem){
        super(mensagem);
    }
}
