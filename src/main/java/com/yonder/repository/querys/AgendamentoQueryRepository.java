package com.yonder.repository.querys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yonder.model.Agendamento;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class AgendamentoQueryRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Agendamento> agendamentoQuery(String dia, String horario, String feito){
		
		StringBuilder query = new StringBuilder("from Agendamento p where ");
		if(dia != null) {
			
			query.append("p.dia  = :dia ");
		}
		if(horario != null) {
			if(query.length() != 25) {
				query.append("and ");
			}
			query.append("p.horario  = :horario ");
		}
		if(feito != null && (feito.equals("true") || feito.equals("false"))) {
			if(query.length() != 25) {
				query.append("and ");
			}
			query.append("p.feito  = :feito");
		}
		
		TypedQuery<Agendamento> typedQuery = em.createQuery(query.toString(), Agendamento.class);
		if(dia != null) {
			typedQuery.setParameter("dia", dia);
		}
		if(horario != null) {
			typedQuery.setParameter("horario", horario);
		}
		if(feito != null && (feito.equals("true") || feito.equals("false"))) {
			if(feito.equals("true")) {
				System.out.println(feito);
				typedQuery.setParameter("feito", true);
			}else if(feito.equals("false")){
				typedQuery.setParameter("feito", false);
			}
		}
		
		return typedQuery.getResultList();
		
	}
}
