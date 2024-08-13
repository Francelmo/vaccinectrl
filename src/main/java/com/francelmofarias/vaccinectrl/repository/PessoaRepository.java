package com.francelmofarias.vaccinectrl.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francelmofarias.vaccinectrl.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("select p from Pessoa p where p.endereco.cidade = :cidade")
    List<Pessoa> findByCidade(@Param("cidade") String cidade);
}

