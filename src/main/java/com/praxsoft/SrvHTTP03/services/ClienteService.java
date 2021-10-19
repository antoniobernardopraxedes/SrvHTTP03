package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import com.praxsoft.SrvHTTP03.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;

    public List<ClienteDb> listar() {

        return clientesRepository.findAll();
    }

    public ClienteDb buscarNomeUsuario(String nomeUsuario) {

        return clientesRepository.findByNomeUsuarioIgnoreCase(nomeUsuario);
    }

    public List<ClienteDb> buscarNomeExato(String nome) {

        return clientesRepository.findByNomeIgnoreCase(nome);
    }

    public List<ClienteDb> buscarNomeParte(String nome) {

        return clientesRepository.findByNomeContainingIgnoreCase(nome);
    }

    public ClienteDb salvarCadastro(Cliente cliente) {

        ClienteDb clienteDb = new ClienteDb();
        clienteDb.setId(null);
        clienteDb.setNomeUsuario(cliente.getNomeUsuario());
        clienteDb.setNome(cliente.getNome());
        clienteDb.setCelular(cliente.getCelular());
        clienteDb.setObs1(cliente.getObs1());
        clienteDb.setObs2(cliente.getObs2());
        clienteDb.setIdoso(cliente.getIdoso());
        clienteDb.setLocomocao(cliente.getLocomocao());
        clienteDb.setExigente(cliente.getExigente());
        clienteDb.setGenero(cliente.getGenero());
        clienteDb.setAdminResp(cliente.getAdminResp());

        return clientesRepository.save(clienteDb);
    }

    public ClienteDb atualizarCadastro(Cliente cliente, long id) {

        ClienteDb clienteDb = new ClienteDb();
        clienteDb.setId(id);
        clienteDb.setNomeUsuario(cliente.getNomeUsuario());
        clienteDb.setNome(cliente.getNome());
        clienteDb.setCelular(cliente.getCelular());
        clienteDb.setObs1(cliente.getObs1());
        clienteDb.setObs2(cliente.getObs2());
        clienteDb.setIdoso(cliente.getIdoso());
        clienteDb.setLocomocao(cliente.getLocomocao());
        clienteDb.setExigente(cliente.getExigente());
        clienteDb.setGenero(cliente.getGenero());
        clienteDb.setAdminResp(cliente.getAdminResp());

        return clientesRepository.save(clienteDb);
    }

    public boolean apagarCadastro(long id) {
        try {
            clientesRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
