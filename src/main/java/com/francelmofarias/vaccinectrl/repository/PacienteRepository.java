package com.francelmofarias.vaccinectrl.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francelmofarias.vaccinectrl.model.Paciente;
import com.francelmofarias.vaccinectrl.model.Pessoa;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.id = :id")
    Optional<Paciente> findByIdCustom(@Param("id") Long id);
}

