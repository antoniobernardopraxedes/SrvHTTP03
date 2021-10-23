package com.praxsoft.SrvHTTP03.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;

@Entity
public class Artigo {

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String nome;
    private String autor;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataPublicacao;

    private String conteudo;

    public Artigo() {}
    public Artigo(String nome) {
        this.nome = nome;
    }

    public long getId() { return Id; }
    public void setId(int id) {
        Id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicacao() {
        return dataPublicacao;
    }

    public void setPublicacao(String publicacao) {
        this.dataPublicacao = publicacao;
    }

}
