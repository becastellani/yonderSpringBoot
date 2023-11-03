package com.yonder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonder.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
