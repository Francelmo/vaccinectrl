package com.francelmofarias.vaccinectrl.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francelmofarias.vaccinectrl.model.ProfissionalSaude;

public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {

    @Query("select p from ProfissionalSaude p left join fetch p.endereco where p.id = :id")
    Optional<ProfissionalSaude> findByIdFetchPessoa(@Param("id") Long id);
}
