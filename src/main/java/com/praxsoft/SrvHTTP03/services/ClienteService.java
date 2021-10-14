package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;

    public List<Cliente> listar() {
        return clientesRepository.findAll();
    }

    public Cliente buscar(Long id) {
        Cliente cliente = clientesRepository.getById(id);

        return cliente;
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setId(null);
        return clientesRepository.save(cliente);
    }

    public void atualizar(Cliente cliente) {
        if (verificarExistencia(cliente)) {
            clientesRepository.save(cliente);
        }
    }

    public void apagar(long id) {
        try {
            clientesRepository.deleteById(id);
        }
        catch (Exception e) {

        }
    }

    private boolean verificarExistencia(Cliente cliente) {
        if (buscar(cliente.getId()) == null) {
            return false;
        }
        else {
            return true;
        }
    }

}
