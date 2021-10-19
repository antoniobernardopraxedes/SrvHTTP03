package com.praxsoft.SrvHTTP03.repository;

import com.praxsoft.SrvHTTP03.domain.ReservaDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservasRepository extends JpaRepository<ReservaDb, Long> {

    List<ReservaDb> findByDataReserva(String dataReserva);

}
