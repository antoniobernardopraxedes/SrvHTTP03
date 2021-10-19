package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.ReservaDb;
import com.praxsoft.SrvHTTP03.domain.ReservaMesa;
import com.praxsoft.SrvHTTP03.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservasRepository reservasRepository;

    public List<ReservaDb> buscarDataReserva(String dataReserva) {

        return reservasRepository.findByDataReserva(dataReserva);
    }

    public ReservaDb salvarReserva(ReservaMesa reservaMesa) {

        ReservaDb reservaDb = new ReservaDb();
        reservaDb.setId(null);
        reservaDb.setMesaSelecionada(reservaMesa.getMesaSelecionada());
        reservaDb.setDataReserva(reservaMesa.getDataReserva());
        reservaDb.setNomeUsuario(reservaMesa.getNomeUsuario());
        reservaDb.setNomeCliente(reservaMesa.getNomeCliente());
        reservaDb.setNumPessoas(reservaMesa.getNumPessoas());
        reservaDb.setHoraChegada(reservaMesa.getHoraChegada());
        reservaDb.setAdminResp(reservaMesa.getAdminResp());
        reservaDb.setHoraRegistro(ImpHora());
        reservaDb.setDataRegistro(ImpData());

        return reservasRepository.save(reservaDb);
    }

    public ReservaDb atualizarReserva(ReservaMesa reservaMesa, long id) {

        ReservaDb reservaDb = new ReservaDb();
        reservaDb.setId(id);
        reservaDb.setMesaSelecionada(reservaMesa.getMesaSelecionada());
        reservaDb.setDataReserva(reservaMesa.getDataReserva());
        reservaDb.setNomeUsuario(reservaMesa.getNomeUsuario());
        reservaDb.setNomeCliente(reservaMesa.getNomeCliente());
        reservaDb.setNumPessoas(reservaMesa.getNumPessoas());
        reservaDb.setHoraChegada(reservaMesa.getHoraChegada());
        reservaDb.setAdminResp(reservaMesa.getAdminResp());
        reservaDb.setHoraRegistro(ImpHora());
        reservaDb.setDataRegistro(ImpData());

        return reservasRepository.save(reservaDb);
    }

    public boolean apagarReserva(long id) {
        try {
            reservasRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    //******************************************************************************************************************
    // Nome do Método: ImpHora                                                                                         *
    //                                                                                                                 *
    // Funcao: gera uma string com a hora recebida em um array de bytes                                                *
    //                                                                                                                 *
    // Entrada: Array[6] [0] = Hora, [1] = Minutos, [2] = Segundos                                                     *
    //                                                                                                                 *
    // Saida: string no formato HH:MM:SS                                                                               *                                                                                 *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public String ImpHora() {
        LocalDateTime datahora = LocalDateTime.now();
        int Hora = datahora.getHour();
        int Minuto = datahora.getMinute();
        int Segundo = datahora.getSecond();

        String Msg = "";
        if (Hora < 10) { Msg = Msg + "0"; }
        Msg = Msg + Hora + ":";
        if (Minuto < 10) { Msg = Msg + "0"; }
        Msg = Msg + Minuto + ":";
        if (Segundo < 10) { Msg = Msg + "0"; }
        Msg = Msg + Segundo;

        return (Msg);
    }

    //******************************************************************************************************************
    // Nome do Método: ImpData                                                                                         *
    //                                                                                                                 *
    // Funcao: gera uma string com a data recebida em um array de bytes                                                *
    //                                                                                                                 *
    // Entrada: Array[6] [3] = Dia, [4] = Mês, [5] = Ano centena, [6] = Ano unidade                                    *
    //                                                                                                                 *
    // Saida: string no formato HH:MM:SS - DD/MM/AAAA                                                                  *                                                                                 *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public String ImpData() {
        LocalDateTime datahora = LocalDateTime.now();
        int Dia = datahora.getDayOfMonth();
        int Mes = datahora.getMonthValue();
        int Ano = datahora.getYear();

        String Msg = "";
        if (Dia < 10) { Msg = Msg + "0"; }
        Msg = Msg + Dia + "/";
        if (Mes < 10) { Msg = Msg + "0"; }
        Msg = Msg + Mes + "/" + Ano;

        return (Msg);
    }

}
