package com.aula.restAPI.agenda.pessoas.repository;

import com.aula.restAPI.agenda.pessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
