package com.desafio.joao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany
    @JoinColumn(name = "trabalhador_id")
    @JsonManagedReference
    private List<Trabalhador> trabalhador;

    @ManyToMany
    @JoinColumn(name = "setor_id")
    @JsonManagedReference
    private Setor setor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Trabalhador> getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(List<Trabalhador> trabalhador) {
        this.trabalhador = trabalhador;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
