package com.praxsoft.SrvHTTP03.domain;

import javax.persistence.*;

@Entity
public class ArtigoDb {

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String data;
    private String nomeArquivo;

    private String subTitulo01;
    private String subTitulo02;
    private String subTitulo03;
    private String subTitulo04;
    private String subTitulo05;
    private String subTitulo06;
    private String subTitulo07;
    private String subTitulo08;
    private String subTitulo09;
    private String subTitulo10;

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

    public String getData() {
        return data;
    }
    public void setData(String publicacao) {
        this.data = publicacao;
    }

    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }

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

    public String getSubTitulo06() { return subTitulo06; }
    public void setSubTitulo06(String subTitulo06) { this.subTitulo06 = subTitulo06; }

    public String getSubTitulo07() { return subTitulo07; }
    public void setSubTitulo07(String subTitulo07) { this.subTitulo07 = subTitulo07; }

    public String getSubTitulo08() { return subTitulo08; }
    public void setSubTitulo08(String subTitulo08) { this.subTitulo08 = subTitulo08; }

    public String getSubTitulo09() { return subTitulo09; }
    public void setSubTitulo09(String subTitulo09) { this.subTitulo09 = subTitulo09; }

    public String getSubTitulo10() { return subTitulo10; }
    public void setSubTitulo10(String subTitulo10) { this.subTitulo10 = subTitulo10; }

    public void MostraCamposArtigo() {
        System.out.println("Id = " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Data da publicação: " + data);
        System.out.println("Nome do arquivo: " + nomeArquivo);
        System.out.println("Sub Título 1: " + subTitulo01);
        System.out.println("Sub Título 2: " + subTitulo02);
        System.out.println("Sub Título 3: " + subTitulo03);
        System.out.println("Sub Título 4: " + subTitulo04);
        System.out.println("Sub Título 5: " + subTitulo05);
        System.out.println("Sub Título 6: " + subTitulo06);
        System.out.println("Sub Título 7: " + subTitulo07);
        System.out.println("Sub Título 8: " + subTitulo08);
        System.out.println("Sub Título 9: " + subTitulo09);
        System.out.println("Sub Título 10: " + subTitulo10);

    }
}
