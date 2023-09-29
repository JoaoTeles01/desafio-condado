package com.desafio.joao.service;

import com.desafio.joao.entity.Cargo;
import com.desafio.joao.exceptions.MesmoNomeCargo;
import com.desafio.joao.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    public CargoRepository repoCargo;

    public Cargo cadastrarCargo(Cargo newCargo) throws MesmoNomeCargo{

        boolean cargoJaCadastrado = repoCargo.existeNomeSetor(newCargo.getNome(), newCargo.getSetor());

        if(cargoJaCadastrado){
            throw new MesmoNomeCargo("Cargo já cadastrado");
        }

        Cargo cargo = new Cargo();
        cargo.setSetor(newCargo.getSetor());
        cargo.setNome(newCargo.getNome());
        return repoCargo.save(cargo);
    }
}
