package com.praxsoft.SrvHTTP03.resources;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.services.ClienteService;
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
    private ClienteService clienteService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Cliente>> Listar() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(clienteService.listar());
    }

    @GetMapping(value = "/salvar1")
    public ResponseEntity<?> Salvar1() {

        Cliente cliente = new Cliente();
        cliente.setNomeUsuario("IsisDias");
        cliente.setNome("Isis Dias Vieira");
        cliente.setCelular("(61) 9 9622-2604");
        cliente.setObs1("Observação 1");
        cliente.setObs2("Observação 2");
        cliente.setIdoso("sim");
        cliente.setLocomocao("não");
        cliente.setExigente("sim");
        cliente.setGenero("feminino");
        cliente.setAdminResp("Bernardo");

        clienteService.salvar(cliente);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(cliente);
    }

    @GetMapping(value = "/salvar2")
    public ResponseEntity<?> Salvar2() {

        Cliente cliente = new Cliente();
        cliente.setNomeUsuario("IrisVieira");
        cliente.setNome("Iris Dias Vieira");
        cliente.setCelular("(21) 9 9653-4328");
        cliente.setObs1("Gosta de sevichi");
        cliente.setObs2("Cozinha bem");
        cliente.setIdoso("sim");
        cliente.setLocomocao("não");
        cliente.setExigente("não");
        cliente.setGenero("masculino");
        cliente.setAdminResp("Bernardo");

        clienteService.salvar(cliente);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/json"))
                .body(cliente);
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
