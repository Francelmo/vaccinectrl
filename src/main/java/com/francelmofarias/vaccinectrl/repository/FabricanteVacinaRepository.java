package com.francelmofarias.vaccinectrl.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francelmofarias.vaccinectrl.model.FabricanteVacina;

public interface FabricanteVacinaRepository extends JpaRepository<FabricanteVacina, Long> {

    @Query("SELECT f FROM FabricanteVacina f WHERE f.quantidade > :quantidade")
    List<FabricanteVacina> findByQuantidadeGreaterThan(@Param("quantidade") int quantidade);

    @Query("SELECT f FROM FabricanteVacina f WHERE f.lote = :lote AND f.validade > CURRENT_DATE")
    Optional<FabricanteVacina> findByLoteAndValidade(@Param("lote") String lote);
}
