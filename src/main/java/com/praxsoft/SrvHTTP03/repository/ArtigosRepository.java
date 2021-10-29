package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.ArtigoDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigosRepository extends JpaRepository<ArtigoDb, Long> {

    List<ArtigoDb> findByTituloIgnoreCase(String titulo);

    List<ArtigoDb> findByTituloContainingIgnoreCase(String titulo);

    List<ArtigoDb> findByAutorIgnoreCase(String autor);

    List<ArtigoDb> findBySubTitulo01ContainingIgnoreCase(String subTitulo01);

}
