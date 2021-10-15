package com.praxsoft.SrvHTTP03.services;

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

    public ClienteDb buscar(Long id) {
        ClienteDb clienteDb = clientesRepository.getById(id);

        return clienteDb;
    }

    public ClienteDb salvar(ClienteDb clienteDb) {
        clienteDb.setId(null);
        return clientesRepository.save(clienteDb);
    }

    public void atualizar(ClienteDb clienteDb) {
        if (verificarExistencia(clienteDb)) {
            clientesRepository.save(clienteDb);
        }
    }

    public void apagar(long id) {
        try {
            clientesRepository.deleteById(id);
        }
        catch (Exception e) {

        }
    }

    private boolean verificarExistencia(ClienteDb clienteDb) {
        if (buscar(clienteDb.getId()) == null) {
            return false;
        }
        else {
            return true;
        }
    }

}
