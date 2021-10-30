package com.praxsoft.SrvHTTP03.domain;

import javax.persistence.*;

@Entity
public class ArtigoBD {

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String dataPublicacao;
    private String dataRegistro;
    private String palavrasChave;
    private String subTitulo01;
    private String subTitulo02;
    private String subTitulo03;
    private String subTitulo04;
    private String subTitulo05;
    private String nomeArquivo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getPalavrasChave() { return palavrasChave; }
    public void setPalavrasChave(String palavrasChave) { this.palavrasChave = palavrasChave; }

    public String getSubTitulo01() { return subTitulo01; }
    public void setSubTitulo01(String subTitulo01) { this.subTitulo01 = subTitulo01; }

    public String getSubTitulo02() { return subTitulo02; }
    public void setSubTitulo02(String subTitulo02) { this.subTitulo02 = subTitulo02; }

    public String getSubTitulo03() { return subTitulo03; }
    public void setSubTitulo03(String subTitulo03) { this.subTitulo03 = subTitulo03; }

    public String getSubTitulo04() { return subTitulo04; }
    public void setSubTitulo04(String subTitulo04) { this.subTitulo04 = subTitulo04; }

    public String getSubTitulo05() { return subTitulo05; }
    public void setSubTitulo05(String subTitulo05) { this.subTitulo05 = subTitulo05; }

    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }
}
