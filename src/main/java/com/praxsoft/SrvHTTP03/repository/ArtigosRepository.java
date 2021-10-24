package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.Artigo;
import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigosRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByTituloIgnoreCase(String titulo);

    List<Artigo> findByTituloContainingIgnoreCase(String titulo);

    List<Artigo> findByAutorIgnoreCase(String autor);

}
