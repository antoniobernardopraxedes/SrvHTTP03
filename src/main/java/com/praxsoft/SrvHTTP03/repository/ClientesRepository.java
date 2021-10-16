package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.Cliente;
import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientesRepository extends JpaRepository<ClienteDb, Long> {

    List<ClienteDb> findByNomeUsuario(String nomeUsuario);

    List<ClienteDb> findByNome(String nome);

}
