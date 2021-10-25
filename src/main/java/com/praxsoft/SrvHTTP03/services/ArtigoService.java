package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.Artigo;
import com.praxsoft.SrvHTTP03.domain.ArtigoDb;
import com.praxsoft.SrvHTTP03.repository.ArtigosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ArtigoService {

    @Autowired
    private ArtigosRepository artigosRepository;

    public List<ArtigoDb> listar() {
        return artigosRepository.findAll();
    }

    public List<ArtigoDb> buscarTitulo(String titulo) {
        return artigosRepository.findByTituloIgnoreCase(titulo);
    }

    public List<ArtigoDb> buscarTituloContem(String titulo) {
        return artigosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<ArtigoDb> buscarAutor(String autor) {
        return artigosRepository.findByAutorIgnoreCase(autor);
    }

    public ArtigoDb salvarArtigo(ArtigoDb artigo) {
        artigo.setId(null);
        return artigosRepository.save(artigo);
    }

    public ArtigoDb atualizarArtigo(ArtigoDb artigo, long id) {
        artigo.setId(id);
        return artigosRepository.save(artigo);
    }

    public boolean apagarArtigo(long id) {
        try {
            artigosRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ArtigoDb cadastrarArtigo(Artigo artigo) {
        boolean resultado = true;

        ArtigoDb artigoDb = new ArtigoDb();
        artigoDb.setId(null);
        artigoDb.setTitulo(artigo.getTitulo());
        artigoDb.setAutor(artigo.getAutor());
        artigoDb.setData(artigo.getData());

        StringTokenizer parseNome = new StringTokenizer(artigo.getAutor());
        String primeiroNomeAutor = parseNome.nextToken();
        StringTokenizer parseTitulo = new StringTokenizer(artigo.getTitulo());
        String primeiroTitulo = parseTitulo.nextToken();

        String nomeArquivo = ImpDataHora() + primeiroNomeAutor.toLowerCase() + primeiroTitulo.toLowerCase() + ".txt";
        artigoDb.setNomeArquivo(nomeArquivo);

        artigoDb.setSubTitulo01(artigo.getSubTitulo01());
        artigoDb.setSubTitulo02(artigo.getSubTitulo02());
        artigoDb.setSubTitulo03(artigo.getSubTitulo03());
        artigoDb.setSubTitulo04(artigo.getSubTitulo04());
        artigoDb.setSubTitulo05(artigo.getSubTitulo05());
        artigoDb.setSubTitulo06(artigo.getSubTitulo06());
        artigoDb.setSubTitulo07(artigo.getSubTitulo07());
        artigoDb.setSubTitulo08(artigo.getSubTitulo08());
        artigoDb.setSubTitulo09(artigo.getSubTitulo09());
        artigoDb.setSubTitulo10(artigo.getSubTitulo10());

        return artigosRepository.save(artigoDb);
    }

    public Artigo buscarArtigoId(Long id) {

        ArtigoDb artigoDb = artigosRepository.getById(id);
        Artigo artigo = new Artigo();

        return artigo;

    }

    //******************************************************************************************************************
    // Nome do Método: ImpDataHora                                                                                     *
    //                                                                                                                 *
    // Funcao: gera uma string com a data e a hora                                                                     *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: string com a data e a hora no formato AAAA-MM-DD-HH-MM-SS                                                *                                                                                 *
    //******************************************************************************************************************
    //
    public String ImpDataHora() {
        LocalDateTime datahora = LocalDateTime.now();
        int Dia = datahora.getDayOfMonth();
        int Mes = datahora.getMonthValue();
        int Ano = datahora.getYear();
        int Hora = datahora.getHour();
        int Minuto = datahora.getMinute();
        int Segundo = datahora.getSecond();

        String Msg = Ano + "-";
        if (Mes < 10) { Msg = Msg + "0"; }
        Msg = Msg + Mes;
        if (Dia < 10) { Msg = Msg + "0"; }
        Msg = Msg + Dia;
        if (Hora < 10) { Msg = Msg + "0"; }
        Msg = Msg + Hora;
        if (Minuto < 10) { Msg = Msg + "0"; }
        Msg = Msg + Minuto;
        if (Segundo < 10) { Msg = Msg + "0"; }
        Msg = Msg + Segundo;

        return (Msg);
    }

    //******************************************************************************************************************
    // Nome da Rotina: IntToStr2                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo na faixa de 00 a 99 para uma string                                        *
    //                                                                                                                 *
    // Entrada: valor inteiro de 0 a 99                                                                                *
    //                                                                                                                 *
    // Saida: string de dois dígitos do número (formato 00 a 99). Se o valor estiver fora da faixa retorna 00          *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    private static String IntToStr2(int valInt) {
        String valStr = "00";
        if ((valInt >= 0) && (valInt < 100)) {
            if (valInt < 10) {
                valStr = ("0" + valInt);
            } else {
                valStr = (valInt + "");
            }
        }
        return (valStr);
    }

    //******************************************************************************************************************
    // Nome da Rotina: IntToStr4                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo na faixa de 0000 a 9999 para uma string                                    *
    //                                                                                                                 *
    // Entrada: valor inteiro de 0 a 9999                                                                              *
    //                                                                                                                 *
    // Saida: string de dois dígitos do número (formato 00 a 99). Se o valor estiver fora da faixa retorna 00          *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    private String IntToStr4(int valInt) {
        String valStr = "0000";

        if ((valInt >= 0) && (valInt <= 9999)) {
            if (valInt >= 1000) {
                valStr = valInt + "";
            } else {
                if (valInt >= 100) {
                    valStr = "0" + valInt;
                } else {
                    if (valInt >= 10) {
                        valStr = "00" + valInt;
                    } else {
                        valStr = "000" + valInt;
                    }
                }
            }
        }
        return (valStr);
    }

}
