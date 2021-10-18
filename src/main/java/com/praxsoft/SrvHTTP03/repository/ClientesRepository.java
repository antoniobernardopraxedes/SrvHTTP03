package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientesRepository extends JpaRepository<ClienteDb, Long> {

    ClienteDb findByNomeUsuarioIgnoreCase(String nomeUsuario);

    List<ClienteDb> findByNomeIgnoreCase(String nome);

    List<ClienteDb> findByNomeContainingIgnoreCase(String nome);
}
