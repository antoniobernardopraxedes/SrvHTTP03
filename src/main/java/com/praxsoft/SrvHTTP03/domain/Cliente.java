package com.praxsoft.SrvHTTP03.domain;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomeUsuario;

    @Column
    private String nome;

    @Column
    private String celular;

    @Column
    private String obs1;

    @Column
    private String obs2;

    @Column
    private String idoso;

    @Column
    private String locomocao;

    @Column
    private String exigente;

    @Column
    private String genero;

    @Column
    private String adminResp;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public String getNomeUsuario() { return nomeUsuario; }

    public String getNome() { return nome; }

    public String getCelular() { return celular; }

    public String getObs1() {
        if (obs1.equals("")) { obs1 = "não informada"; }
        return obs1;
    }

    public String getObs2() {
        if (obs2.equals("")) { obs2 = "não informada"; }
        return obs2;
    }

    public String getIdoso() { return idoso; }

    public String getLocomocao() { return locomocao; }

    public String getExigente() { return exigente; }

    public String getGenero() { return genero; }

    public String getAdminResp() { return adminResp; }

    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public void setNome(String nome) { this.nome = nome; }

    public void setCelular(String celular) { this.celular = celular; }

    public void setObs1(String obs1) { this.obs1 = obs1; }

    public void setObs2(String obs2) { this.obs2 = obs2; }

    public void setIdoso(String idoso) { this.idoso = idoso; }

    public void setLocomocao(String locomocao) { this.locomocao = locomocao; }

    public void setExigente(String exigente) { this.exigente = exigente; }

    public void setGenero(String genero) { this.genero = genero; }

    public void setAdminResp(String adminResp) { this.adminResp = adminResp; }

}
