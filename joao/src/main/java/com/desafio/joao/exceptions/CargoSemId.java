package com.desafio.joao.exceptions;

public class CargoSemId extends Exception{

    public CargoSemId(){
        super();
    }

    public CargoSemId(String mensagem){
        super(mensagem);
    }
}
