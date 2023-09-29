package com.desafio.joao.repository;

import com.desafio.joao.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query(value = "SELECT * FROM public.setor WHERE id = ?1", nativeQuery = true)
    public List<Setor> getSetorSameName(String nome);

    @Query(value = "SELECT * FROM public.setor WHERE id = ?1", nativeQuery = true)
    public Setor getSetorById(Integer id);
}
