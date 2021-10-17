package com.praxsoft.SrvHTTP03.resources;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.domain.ReservaMesa;
import com.praxsoft.SrvHTTP03.services.Arquivo;
import com.praxsoft.SrvHTTP03.services.ClienteService;
import com.praxsoft.SrvHTTP03.services.VlglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class VlglResources {

    @Autowired
    private VlglService vlglService;

    @Autowired
    private ClienteService clienteService;

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

        ReservaMesa[] reservaMesas = vlglService.LeArquivoReservaMesa(dataReserva);
        List<ReservaMesa[]> MsgJson = new ArrayList<ReservaMesa[]>(Collections.singleton(reservaMesas));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(MsgJson);
    }

    @PostMapping(value = "/vlgl/reserva")
    public ResponseEntity<?> ConfirmaReserva(@RequestBody ReservaMesa reservaMesa) {
        vlglService.Terminal("Solicitação de reserva de mesa", false);

        if (vlglService.EscreveArquivoReservaMesa(reservaMesa)) {
            reservaMesa.setHoraRegistro(vlglService.ImpHora());
            reservaMesa.setDataRegistro(vlglService.ImpData());
            vlglService.GeraArquivoImpressaoReserva(reservaMesa);
            vlglService.GeraArquivoRegistroReserva(reservaMesa);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(reservaMesa);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED )
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraReservaVazia());
        }
    }

    @DeleteMapping(value = "/vlgl/reserva/exclui/{dataReserva}/{idMesa}")
    public ResponseEntity<?> ExcluiReserva(@PathVariable String dataReserva, @PathVariable String idMesa) {
        vlglService.Terminal("Solicitação de exclusão: " + dataReserva + " - Mesa: " + idMesa, false);

        ReservaMesa reservaMesa = vlglService.GeraReservaLivre(dataReserva, idMesa);
        if (vlglService.EscreveArquivoReservaMesa(reservaMesa)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            vlglService.GeraArquivoExclusaoReserva(dataReserva, idMesa, auth.getName());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(reservaMesa);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED )
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraReservaVazia());
        }
    }

    @GetMapping(value = "/vlgl/reserva/consulta/{dataReservaidMesa}")
    public ResponseEntity<?> ConsultaReserva(@PathVariable String dataReservaidMesa) {
        String dataReserva = dataReservaidMesa.substring(0, 10);
        String idMesa = dataReservaidMesa.substring(10, 13);
        vlglService.Terminal("Solicitação de consulta - Data: " + dataReserva + " - Mesa: " + idMesa, false);

        ReservaMesa reservaMesa = vlglService.ConsultaReservaMesa(dataReserva, idMesa);
        vlglService.GeraArquivoImpressaoReserva(reservaMesa);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(reservaMesa);
    }

    @GetMapping(value = "/vlgl/reserva/impressao")
    public ResponseEntity<?> EnviaArquivoImpressao() {
        vlglService.Terminal("Solicitação do arquivo de impressão", false);

        String caminho = Arquivo.getDiretorioBd() + "relatorios/";
        String nomeArquivo = vlglService.getNomeArquivoImpressao();
        String arquivoImpressao = Arquivo.LeTexto(caminho, nomeArquivo);

        if (arquivoImpressao != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("text/html"))
                    .header("Content-Disposition", "attachment; filename=" + nomeArquivo)
                    .body(arquivoImpressao);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND )
                    .contentType(MediaType.valueOf("text/html"))
                    .body("Arquivo não encontrado: " + nomeArquivo);
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

        Cliente cliente = vlglService.LeArquivoCadastroCliente(nomeUsuario);
        if (!cliente.getNomeUsuario().equals("null")) {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(cliente);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(cliente);
        }
    }

    @PostMapping(value = "/vlgl/cadastro/cliente")
    public ResponseEntity<?> CadastraCliente(@RequestBody Cliente cliente) {
        vlglService.Terminal("Cadastro de cliente: " + cliente.getNomeUsuario(), false);

        if (vlglService.GeraCadastroCliente(cliente)) {

            clienteService.salvarCliente(cliente);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(cliente);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED )
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraCadastroClienteVazio());
        }
    }

    @PutMapping(value = "/vlgl/cadastro/cliente")
    public ResponseEntity<?> AtualizaCadastroCliente(@RequestBody Cliente cliente) {
        String nomeUsuario = cliente.getNomeUsuario();
        vlglService.Terminal("Atualização de cadastro de cliente: " + nomeUsuario, false);

        if (vlglService.AtualizaCadastroCliente(cliente)) {



            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.LeArquivoCadastroCliente(nomeUsuario));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED )
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraCadastroClienteVazio());
        }
    }

    @DeleteMapping(value = "/vlgl/cadastro/cliente/{nomeUsuario}")
    public ResponseEntity<?> ExcluiCadastroCliente(@PathVariable String nomeUsuario) {
        vlglService.Terminal("Exclusão de cadastro de cliente: " + nomeUsuario, false);

        if (vlglService.ExcluiCadastroCliente(nomeUsuario)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraCadastroClienteVazio());
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(vlglService.GeraCadastroClienteVazio());
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
