package com.praxsoft.SrvHTTP03.resources;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import com.praxsoft.SrvHTTP03.domain.ReservaDb;
import com.praxsoft.SrvHTTP03.domain.ReservaMesa;
import com.praxsoft.SrvHTTP03.services.Arquivo;
import com.praxsoft.SrvHTTP03.services.ClienteService;
import com.praxsoft.SrvHTTP03.services.ReservaService;
import com.praxsoft.SrvHTTP03.services.VlglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class VlglResources {

    @Autowired
    private VlglService vlglService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping(value = "/vlgl/admin")
    public ResponseEntity<?> EnviaDadosAdmin() {
        vlglService.Terminal("Método GET - Recurso solicitado: /vlgl/admin", false);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String MsgJson = "{ \"nomeUsuarioAdmin\" : \"" + auth.getName() + "\" }";

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(MsgJson);
    }

    @GetMapping(value = "/vlgl/reservas")
    public ResponseEntity<?> ReservasVlgl(@RequestHeader(value = "User-Agent") String userAgent) {
        vlglService.Terminal("Método GET - Recurso solicitado: /vlgl/reservas", false);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeArquivo = "adminreservasbd.html";
        String caminho = Arquivo.getDiretorioRecursos() + "/vlgl/";

        return vlglService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @GetMapping(value = "/vlgl/reservas/data/{dataReserva}")
    public ResponseEntity<?> VerificaData(@PathVariable String dataReserva) {
        vlglService.Terminal("Solicitação de reservas na data: " + dataReserva, false);

        List<ReservaDb> reservaDbList = reservaService.buscarDataReserva(dataReserva);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(reservaDbList);
    }

    @PostMapping(value = "/vlgl/reserva")
    public ResponseEntity<?> ConfirmaReserva(@RequestBody ReservaMesa reservaMesa) {
        vlglService.Terminal("Solicitação de reserva de mesa", false);

        ReservaDb reservaDb = reservaService.salvarReserva(reservaMesa);
        if (reservaDb == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.GeraArquivoImpressaoReserva(reservaDb);
            vlglService.GeraArquivoRegistroReserva(reservaDb);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(reservaDb);
        }
    }

    @PutMapping(value = "/vlgl/reserva/{idReserva}")
    public ResponseEntity<?> AtualizaReserva(@PathVariable long idReserva, @RequestBody ReservaMesa reservaMesa) {
        vlglService.Terminal("Solicitação de reserva de mesa", false);

        ReservaDb reservaDb = reservaService.atualizarReserva(reservaMesa, idReserva);
        if (reservaDb == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.GeraArquivoImpressaoReserva(reservaDb);
            vlglService.GeraArquivoRegistroReserva(reservaDb);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(reservaDb);
        }
    }

    @DeleteMapping(value = "/vlgl/reserva/exclui/{idReserva}")
    public ResponseEntity<?> ExcluiReserva(@PathVariable long idReserva) {
        vlglService.Terminal("Solicitação de exclusão: id = " + idReserva, false);

        ReservaDb reservaDb = reservaService.buscarIdReserva(idReserva);
        if (reservaDb == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            vlglService.GeraArquivoExclusaoReserva(reservaDb.getDataReserva(),
                                                   reservaDb.getMesaSelecionada(), auth.getName());

            if (reservaService.apagarReserva(idReserva)) {
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @GetMapping(value = "/vlgl/reserva/consulta/{idReserva}")
    public ResponseEntity<?> ConsultaReserva(@PathVariable long idReserva) {
        vlglService.Terminal("Solicitação de consulta - id: " + idReserva, false);

        ReservaDb reservaDb = reservaService.buscarIdReserva(idReserva);
        if (reservaDb == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.GeraArquivoImpressaoReserva(reservaDb);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(reservaDb);
        }
    }

    @GetMapping(value = "/vlgl/reserva/impressao")
    public ResponseEntity<?> EnviaArquivoImpressao() {
        vlglService.Terminal("Solicitação do arquivo de impressão", false);

        String caminho = Arquivo.getDiretorioBd() + "relatorios/";
        String nomeArquivo = vlglService.getNomeArquivoImpressao();
        String arquivoImpressao = Arquivo.LeTexto(caminho, nomeArquivo);

        if (arquivoImpressao == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("text/html"))
                    .header("Content-Disposition", "attachment; filename=" + nomeArquivo)
                    .body(arquivoImpressao);
        }
    }

    @GetMapping(value = "/vlgl/cadastro")
    public ResponseEntity<?> CadastroVlgl(@RequestHeader(value = "User-Agent") String userAgent) {

        String nomeArquivo = "admincadastrobd.html";
        String caminho = Arquivo.getDiretorioRecursos() + "/vlgl/";

        return vlglService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }

    @GetMapping(value = "/vlgl/cadastro/cliente/{nomeUsuario}")
    public ResponseEntity<?> VerificaCliente(@PathVariable String nomeUsuario) {
        vlglService.Terminal("Verificação de cliente: " + nomeUsuario, false);

        ClienteDb clienteDb = clienteService.buscarNomeUsuario(nomeUsuario);
        if (clienteDb == null) {
            vlglService.Terminal("Cliente não cadastrado.", false);
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.Terminal("Enviados os dados do cliente: " + nomeUsuario, false);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(clienteDb);
        }
    }

    @PostMapping(value = "/vlgl/cadastro/cliente")
    public ResponseEntity<?> CadastraCliente(@RequestBody Cliente cliente) {
        vlglService.Terminal("Cadastro de cliente: " + cliente.getNomeUsuario(), false);

        ClienteDb clienteDb = clienteService.salvarCadastro(cliente);
        if (clienteDb == null) {
            vlglService.Terminal("Falha ao cadastrar o cliente.", false);
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.Terminal("Cliente " + cliente.getNomeUsuario() + " cadastrado.", false);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(clienteDb);
        }
    }

    @PutMapping(value = "/vlgl/cadastro/cliente/{id}")
    public ResponseEntity<?> AtualizaCadastroCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        String nomeUsuario = cliente.getNomeUsuario();
        vlglService.Terminal("Atualização de cadastro de cliente: " + nomeUsuario, false);

        ClienteDb clienteDb = clienteService.atualizarCadastro(cliente, id);
        if (clienteDb == null) {
            vlglService.Terminal("Falha ao atualizar o cadastro do cliente.", false);
            return ResponseEntity.notFound().build();
        }
        else {
            vlglService.Terminal("Cadastro do Cliente atualizado.", false);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(clienteDb);
        }
    }

    @DeleteMapping(value = "/vlgl/cadastro/cliente/{id}")
    public ResponseEntity<?> ExcluiCadastroCliente(@PathVariable long id) {
        vlglService.Terminal("Exclusão de cadastro de cliente: id = " + id, false);

        if (clienteService.apagarCadastro(id)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/vlgl/aux/{recurso}")
    public ResponseEntity<?> EnviaArquivoVlgl(@PathVariable("recurso") String nomeArquivo,
                                              @RequestHeader(value = "User-Agent") String userAgent) {

        vlglService.Terminal("Método GET - Recurso solicitado: /vlgl/aux/" + nomeArquivo, false);
        String caminho = Arquivo.getDiretorioRecursos() + "/vlgl/";

        return vlglService.LeArquivoMontaResposta(caminho, nomeArquivo, userAgent);
    }
}
