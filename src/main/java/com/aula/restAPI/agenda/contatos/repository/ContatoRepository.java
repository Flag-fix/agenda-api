package com.aula.restAPI.agenda.contatos.repository;

import com.aula.restAPI.agenda.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
