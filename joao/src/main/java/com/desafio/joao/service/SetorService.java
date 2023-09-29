package com.desafio.joao.service;

import com.desafio.joao.entity.Setor;
import com.desafio.joao.exceptions.SetorNomesIguais;
import com.desafio.joao.exceptions.SetorNull;
import com.desafio.joao.exceptions.SetorSemId;
import com.desafio.joao.repository.CargoRepository;
import com.desafio.joao.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository repoSetor;

    @Autowired
    private CargoRepository repoCargo;

    public Setor cadastrarSetor(Setor newSetor) throws SetorNomesIguais {
        List<Setor> setoresComMesmoNome = repoSetor.getSetorSameName(newSetor.getNome());
        if (!setoresComMesmoNome.isEmpty()) {
            throw new SetorNomesIguais("Setor já existente");
        }
        return repoSetor.save(newSetor);
    }

    public Setor getPorId(Integer id) {
        return repoSetor.findById(id).orElse(null);
    }

    public Setor salvarSetor(Setor setor) {
        try {
            return repoSetor.save(setor);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Setor> buscarSetores() throws SetorNull {
        List<Setor> findAllSetores = repoSetor.findAll();
        if (findAllSetores == null) {
            throw new SetorNull("Nenhum setor encontrado");
        }
        return findAllSetores;
    }

    public Setor buscaSetorId(Integer id) {
        return repoSetor.getSetorById(id);
    }

    public void deletaSetor(@PathVariable Integer id) throws SetorSemId {
        Setor setorId = buscaSetorId(id);
        if (setorId == null) {
            throw new SetorSemId("Não foi possivel deletar!");
        } else {
            repoSetor.deleteById(id);
        }
    }

}
