package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.ClienteDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<ClienteDb, Long> {
}
