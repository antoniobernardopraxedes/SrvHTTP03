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

    public List<ClienteDb> buscarNomeUsuario(String nomeUsuario) {

        return clientesRepository.findByNomeUsuario(nomeUsuario);
    }

    public List<ClienteDb> buscarNome(String nome) {

        return clientesRepository.findByNome(nome);
    }

    public ClienteDb salvarCliente(Cliente cliente) {

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

    public ClienteDb salvar(ClienteDb clienteDb) {
        clienteDb.setId(null);
        return clientesRepository.save(clienteDb);
    }

    public void apagar(long id) {
        try {
            clientesRepository.deleteById(id);
        }
        catch (Exception e) {

        }
    }

}
