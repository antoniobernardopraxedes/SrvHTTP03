package com.praxsoft.SrvHTTP03.resources;

import com.praxsoft.SrvHTTP03.domain.Artigo;
import com.praxsoft.SrvHTTP03.domain.ArtigoBD;
import com.praxsoft.SrvHTTP03.services.Arquivo;
import com.praxsoft.SrvHTTP03.services.ArtigoService;
import com.praxsoft.SrvHTTP03.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SiteResources {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ArtigoService artigoService;

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

    @GetMapping(value = "/isis/artigo")
    public ResponseEntity<?> PesquisaArtigo(@RequestHeader(value = "User-Agent") String userAgent) {
        String nomeArquivo = "pesquisaartigo.html";

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/";
        return siteService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @PostMapping(value = "/isis/cadastro")
    public ResponseEntity<?> CadastrarArtigo(@RequestBody Artigo artigo) {
        System.out.println("Solicitação de cadastro de artigo");

        ArtigoBD artigoBD = artigoService.cadastrarArtigo(artigo);

        if (artigoBD == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(artigoBD);
        }
    }

    @PutMapping(value = "/isis/atualiza/{id}")
    public ResponseEntity<?> AtualizarArtigo(@PathVariable Long id, @RequestBody Artigo artigo) {
        System.out.println("Solicitação de atualização de artigo");

        ArtigoBD artigoBD = artigoService.atualizarArtigo(artigo, id);

        if (artigoBD == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(artigoBD);
        }
    }

    @GetMapping(value = "/isis/listar")
    public ResponseEntity<?> ListaArtigos() {
        System.out.println("Solicitação de listagens de artigos");

        List<ArtigoBD> listagemArtigos = artigoService.listar();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(listagemArtigos);
    }

    @GetMapping(value = "/isis/buscaid/{id}")
    public ResponseEntity<?> BuscaArtigoId(@PathVariable Long id) {
        System.out.println("Solicitação de busca de artigo por identificador");

        Artigo artigo = artigoService.buscarArtigoId(id);

        if (artigo == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(artigo);
        }
    }

    @GetMapping(value = "/isis/buscatitulo/{titulo}")
    public ResponseEntity<?> BuscaArtigoTitulo(@PathVariable String titulo) {
        System.out.println("Solicitação de busca de artigo por título");

        List<ArtigoBD> ListaArtigos = artigoService.buscarTituloContem(titulo);

        if (ListaArtigos == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(ListaArtigos);
        }
    }

    @GetMapping(value = "/isis/buscasubtitulo/{subtitulo}")
    public ResponseEntity<?> BuscaArtigoSubTitulo01(@PathVariable String subtitulo) {
        System.out.println("Solicitação de busca de artigo por palavra chave");

        List<ArtigoBD> ListaArtigos = artigoService.buscarSubTitulo01Contem(subtitulo);

        if (ListaArtigos == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(ListaArtigos);
        }
    }

    @DeleteMapping(value = "/isis/apaga/{id}")
    public ResponseEntity<?> ApagaArtigo(@PathVariable Long id) {
        System.out.println("Solicitação de exclusão de artigo");

        if (artigoService.apagarArtigo(id)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/isis/ler/{nomeArquivo}")
    public ResponseEntity<?> EnviaParagrafos(@PathVariable String nomeArquivo) {
        System.out.println("Solicitação de leitura de conteúdo de arquivo por parágrafos");

        String caminho = Arquivo.getDiretorioRecursos() + "/isis/textos/";
        List <String> paragrafos = Arquivo.LeParagrafos(caminho, nomeArquivo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(paragrafos);
    }

}
