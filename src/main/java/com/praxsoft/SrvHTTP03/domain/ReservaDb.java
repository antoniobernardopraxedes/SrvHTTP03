package com.praxsoft.SrvHTTP03.domain;

import com.praxsoft.SrvHTTP03.services.Auxiliar;

import javax.persistence.*;

@Entity
public class ReservaDb {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mesaSelecionada;
    private String dataReserva;
    private String nomeUsuario;
    private String nomeCliente;
    private String numPessoas;
    private String horaChegada;
    private String adminResp;
    private String horaRegistro;
    private String dataRegistro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMesaSelecionada() {
        return mesaSelecionada;
    }
    public void setMesaSelecionada(String mesaSelecionada) { this.mesaSelecionada = mesaSelecionada; }

    public String getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(String dataReserva) { this.dataReserva = dataReserva; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getNumPessoas() {
        return numPessoas;
    }
    public void setNumPessoas(String numPessoas) { this.numPessoas = numPessoas; }

    public String getHoraChegada() { return horaChegada; }
    public void setHoraChegada(String horaChegada) { this.horaChegada = horaChegada; }

    public String getAdminResp() {
        return adminResp;
    }
    public void setAdminResp(String adminResp) { this.adminResp = adminResp; }

    public String getHoraRegistro() { return horaRegistro; }
    public void setHoraRegistro(String horaRegistro) { this.horaRegistro = horaRegistro; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public void MostraCamposTerminal() {
        Auxiliar.Terminal("Identificador: " + id, false);
        Auxiliar.Terminal("Mesa selecionada: " + mesaSelecionada, false);
        Auxiliar.Terminal("Data da reserva: " + dataReserva, false);
        Auxiliar.Terminal("Nome de usu??rio: " + nomeUsuario, false);
        Auxiliar.Terminal("Nome do cliente: " + nomeCliente, false);
        Auxiliar.Terminal("N??mero de Pessoas: " + numPessoas, false);
        Auxiliar.Terminal("Hora de chegada: " + horaChegada, false);
        Auxiliar.Terminal("Hora de registro: " + horaRegistro, false);
        Auxiliar.Terminal("Data de registro: " + dataRegistro, false);
    }

}
