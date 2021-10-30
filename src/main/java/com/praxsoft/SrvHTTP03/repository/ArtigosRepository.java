package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.ArtigoBD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigosRepository extends JpaRepository<ArtigoBD, Long> {

    List<ArtigoBD> findByTituloIgnoreCase(String titulo);

    List<ArtigoBD> findByTituloContainingIgnoreCase(String titulo);

    List<ArtigoBD> findByAutorIgnoreCase(String autor);

    List<ArtigoBD> findBySubTitulo01ContainingIgnoreCase(String subTitulo01);

}
