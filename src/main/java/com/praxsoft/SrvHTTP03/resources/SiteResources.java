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



    @GetMapping(value = "/listar")
    public ResponseEntity<List<ClienteDb>> Listar() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(clienteService.listar());
    }

    @GetMapping(value = "/salvar1")
    public ResponseEntity<?> Salvar1() {

        ClienteDb clienteDb = new ClienteDb();
        clienteDb.setNomeUsuario("IsisDias");
        clienteDb.setNome("Isis Dias Vieira");
        clienteDb.setCelular("(61) 9 9622-2604");
        clienteDb.setObs1("Observação 1");
        clienteDb.setObs2("Observação 2");
        clienteDb.setIdoso("sim");
        clienteDb.setLocomocao("não");
        clienteDb.setExigente("sim");
        clienteDb.setGenero("feminino");
        clienteDb.setAdminResp("Bernardo");

        clienteService.salvar(clienteDb);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(clienteDb);
    }

    @GetMapping(value = "/salvar2")
    public ResponseEntity<?> Salvar2() {

        ClienteDb clienteDb = new ClienteDb();
        clienteDb.setNomeUsuario("IrisVieira");
        clienteDb.setNome("Iris Dias Vieira");
        clienteDb.setCelular("(21) 9 9653-4328");
        clienteDb.setObs1("Gosta de sevichi");
        clienteDb.setObs2("Cozinha bem");
        clienteDb.setIdoso("sim");
        clienteDb.setLocomocao("não");
        clienteDb.setExigente("não");
        clienteDb.setGenero("masculino");
        clienteDb.setAdminResp("Bernardo");

        clienteService.salvar(clienteDb);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(clienteDb);
    }

    @GetMapping(value = "/apagar/{id}")
    public ResponseEntity<?> Apagar(@PathVariable long id) {

        clienteService.apagar(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body("{ \"CadastroApagado\" : \"" + id + "\"}");
    }

}
