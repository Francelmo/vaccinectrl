package com.francelmofarias.vaccinectrl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francelmofarias.vaccinectrl.model.Vacinacao;
import com.francelmofarias.vaccinectrl.model.Paciente;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {

    List<Vacinacao> findByPaciente(Paciente paciente);

    @Query("select v from Vacinacao v left join fetch v.paciente p left join fetch v.profissionalSaude ps left join fetch v.vacina f left join fetch v.local where v.id = :id")
    Optional<Vacinacao> findByIdFetchCompleto(@Param("id") Long id);
}
