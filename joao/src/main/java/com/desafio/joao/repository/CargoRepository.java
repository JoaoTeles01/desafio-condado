package com.desafio.joao.repository;

import com.desafio.joao.entity.Cargo;
import com.desafio.joao.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    boolean existeNomeSetor(String nome, Setor setor);

    @Query(value = "SELECT * FROM public.cargo c WHERE setor_id = ?1", nativeQuery = true)
    public List<Cargo> getCargoById(Integer id);

}
