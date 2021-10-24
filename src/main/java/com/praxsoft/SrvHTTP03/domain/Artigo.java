package com.praxsoft.SrvHTTP03.domain;

import javax.persistence.*;

@Entity
public class Artigo {

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String dataPublicacao;
    private String palavrasChave;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(String publicacao) {
        this.dataPublicacao = publicacao;
    }

    public String getPalavrasChave() { return palavrasChave; }
    public void setPalavrasChave(String palavrasChave) { this.palavrasChave = palavrasChave; }

}
