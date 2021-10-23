package com.praxsoft.SrvHTTP03.resources;

import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import com.praxsoft.SrvHTTP03.services.Arquivo;
import com.praxsoft.SrvHTTP03.services.ClienteService;
import com.praxsoft.SrvHTTP03.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SiteResources {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/isis")
    public ResponseEntity<?> InicioSite(@RequestHeader(value = "User-Agent") String userAgent) {
        String nomeArquivo = "indice.html";

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/";
        return siteService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @GetMapping(value = "/isis/{nomeArquivo}")
    public ResponseEntity<?> EnviaHtmlSite(@PathVariable("nomeArquivo") String nomeArquivo,
                                           @RequestHeader(value = "User-Agent") String userAgent) {

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/";
        return siteService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @GetMapping(value = "/favicon.ico")
    public ResponseEntity<?> EnviaIcone() {
        System.out.println("Enviado arquivo favicon.ico");

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/";
        return siteService.LeArquivoMontaResposta(caminho, "favicon.ico", "null");
    }


    @GetMapping(value = "/isis/novo")
    public ResponseEntity<?> SiteNovo(@RequestHeader(value = "User-Agent") String userAgent) {
        String nomeArquivo = "amorosnovo.html";

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/";
        return siteService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @GetMapping(value = "/isis/ler/{nomeArquivo}")
    public ResponseEntity<?> EnviaParagrafos(@PathVariable String nomeArquivo) {

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/textos/";
        List <String> paragrafos = Arquivo.LeParagrafos(caminho, nomeArquivo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(paragrafos);
    }



}
