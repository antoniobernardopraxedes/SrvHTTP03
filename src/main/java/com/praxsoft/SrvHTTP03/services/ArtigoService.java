package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.Artigo;
import com.praxsoft.SrvHTTP03.domain.ArtigoBD;
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

    public List<ArtigoBD> listar() {
        return artigosRepository.findAll();
    }

    public List<ArtigoBD> buscarTitulo(String titulo) {
        return artigosRepository.findByTituloIgnoreCase(titulo);
    }

    public List<ArtigoBD> buscarTituloContem(String titulo) {
        return artigosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<ArtigoBD> buscarAutor(String autor) {
        return artigosRepository.findByAutorIgnoreCase(autor);
    }

    public List<ArtigoBD> buscarSubTitulo01Contem(String subTitulo01) {
        return artigosRepository.findBySubTitulo01ContainingIgnoreCase(subTitulo01);
    }

    public ArtigoBD salvarArtigo(ArtigoBD artigo) {
        artigo.setId(null);
        return artigosRepository.save(artigo);
    }

    public ArtigoBD atualizarArtigo(ArtigoBD artigo, long id) {
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

    public Artigo buscarArtigoId(Long id) {

        try {
            ArtigoBD artigoBD = artigosRepository.getById(id);
            Artigo artigo = new Artigo();

            artigo.setId(artigoBD.getId());
            artigo.setTitulo(artigoBD.getTitulo());
            artigo.setAutor(artigoBD.getAutor());
            artigo.setDataPublicacao(artigoBD.getDataPublicacao());
            artigo.setDataRegistro(artigoBD.getDataRegistro());
            artigo.setPalavrasChave(artigoBD.getPalavrasChave());

            artigo.setSubTitulo01(artigoBD.getSubTitulo01());
            artigo.setSubTitulo02(artigoBD.getSubTitulo02());
            artigo.setSubTitulo03(artigoBD.getSubTitulo03());
            artigo.setSubTitulo04(artigoBD.getSubTitulo04());
            artigo.setSubTitulo05(artigoBD.getSubTitulo05());

            artigo.setNomeArquivo(artigoBD.getNomeArquivo());

            String caminho = Arquivo.getDiretorioBd() + "/artigos/";
            String conteudo = Arquivo.LeTexto(caminho, artigoBD.getNomeArquivo());
            if (conteudo == null) {
                artigo.setConteudo("");
            } else {
                artigo.setConteudo(conteudo);
            }
            return artigo;
        } catch (Exception e) {
            return null;
        }
    }

    public ArtigoBD cadastrarArtigo(Artigo artigo) {

        ArtigoBD artigoBD = new ArtigoBD();
        artigoBD.setId(null);
        artigoBD.setTitulo(artigo.getTitulo());
        artigoBD.setAutor(artigo.getAutor());
        artigoBD.setDataPublicacao(artigo.getDataPublicacao());
        artigoBD.setDataRegistro(ImpDataHoraRegistro());
        artigoBD.setPalavrasChave(artigo.getPalavrasChave());
        artigoBD.setSubTitulo01(artigo.getSubTitulo01());
        artigoBD.setSubTitulo02(artigo.getSubTitulo02());
        artigoBD.setSubTitulo03(artigo.getSubTitulo03());
        artigoBD.setSubTitulo04(artigo.getSubTitulo04());
        artigoBD.setSubTitulo05(artigo.getSubTitulo05());

        StringTokenizer parseNome = new StringTokenizer(artigo.getAutor());
        String primeiroNomeAutor = parseNome.nextToken();
        StringTokenizer parseTitulo = new StringTokenizer(artigo.getTitulo());
        String primeiroTitulo = parseTitulo.nextToken();

        String nomeArquivo = ImpDataHora() + primeiroNomeAutor.toLowerCase() + primeiroTitulo.toLowerCase() + ".txt";
        artigoBD.setNomeArquivo(nomeArquivo);
        String caminho = Arquivo.getDiretorioBd() + "/artigos/";
        Arquivo.EscreveTexto(caminho, nomeArquivo, artigo.getConteudo());

        return artigosRepository.save(artigoBD);
    }

    public ArtigoBD atualizarArtigo(Artigo artigo, Long id) {

        ArtigoBD artigoBD = artigosRepository.getById(id);
        artigoBD.setId(id);
        artigoBD.setTitulo(artigo.getTitulo());
        artigoBD.setAutor(artigo.getAutor());
        artigoBD.setDataPublicacao(artigo.getDataPublicacao());
        artigoBD.setDataRegistro(ImpDataHoraRegistro());
        artigoBD.setPalavrasChave(artigo.getPalavrasChave());
        artigoBD.setSubTitulo01(artigo.getSubTitulo01());
        artigoBD.setSubTitulo02(artigo.getSubTitulo02());
        artigoBD.setSubTitulo03(artigo.getSubTitulo03());
        artigoBD.setSubTitulo04(artigo.getSubTitulo04());
        artigoBD.setSubTitulo05(artigo.getSubTitulo05());

        String caminho = Arquivo.getDiretorioBd() + "/artigos/";
        String nomeArquivo = artigoBD.getNomeArquivo();
        Arquivo.Renomeia(caminho, nomeArquivo, "a" + nomeArquivo);
        Arquivo.Apaga(caminho, nomeArquivo);
        Arquivo.EscreveTexto(caminho, nomeArquivo, artigo.getConteudo());

        return artigosRepository.save(artigoBD);
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
    // Nome do Método: ImpDataHoraRegistro                                                                             *
    //                                                                                                                 *
    // Funcao: gera uma string com a data e a hora para registro da última modificação                                 *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: string com a data e a hora no formato DD/MM/AAAA - HH:MM:SS                                              *                                                                                 *
    //******************************************************************************************************************
    //
    public String ImpDataHoraRegistro() {
        LocalDateTime datahora = LocalDateTime.now();
        int Dia = datahora.getDayOfMonth();
        int Mes = datahora.getMonthValue();
        int Ano = datahora.getYear();
        int Hora = datahora.getHour();
        int Minuto = datahora.getMinute();
        int Segundo = datahora.getSecond();

        String msg = "";
        if (Dia < 10) { msg = msg + "0"; }
        msg = msg + Dia + "/";
        if (Mes < 10) { msg = msg + "0"; }
        msg = msg + Mes + "/";
        msg = msg + Ano + " - ";

        if (Hora < 10) { msg = msg + "0"; }
        msg = msg + Hora + ":";
        if (Minuto < 10) { msg = msg + "0"; }
        msg = msg + Minuto + ":";
        if (Segundo < 10) { msg = msg + "0"; }
        msg = msg + Segundo;

        return (msg);
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
