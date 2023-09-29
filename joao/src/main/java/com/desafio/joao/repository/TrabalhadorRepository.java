package com.desafio.joao.repository;

import com.desafio.joao.entity.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Integer> {

    @Query(value = "SELECT * FROM public.trabalhador WHERE cpf = ?1", nativeQuery = true)
    public Trabalhador getByCpf(String cpf);
}
