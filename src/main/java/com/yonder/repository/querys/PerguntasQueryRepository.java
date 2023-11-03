package com.yonder.repository.querys;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.yonder.model.Perguntas;
import java.util.List;
@Repository
public class PerguntasQueryRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Perguntas> perguntasQuery(String cabecalho, String tipo_prova, String nivel){
		
		StringBuilder query = new StringBuilder("from Perguntas p where ");
		if(cabecalho != null) {
			
			query.append("p.cabecalho  = :cabecalho ");
		}
		if(tipo_prova != null) {
			if(query.length() != 23) {
				query.append("and ");
			}
			query.append("p.tipo_prova  = :tipo_prova ");
		}
		if(nivel != null) {
			if(query.length() != 23) {
				query.append("and ");
			}
			query.append("p.nivel  = :nivel ");
		}
		
		TypedQuery<Perguntas> typedQuery = em.createQuery(query.toString(), Perguntas.class);
		if(cabecalho != null) {
			typedQuery.setParameter("cabecalho", cabecalho);
		}
		if(tipo_prova != null) {
			typedQuery.setParameter("tipo_prova", tipo_prova);
		}
		if(nivel != null) {
			typedQuery.setParameter("nivel", nivel);
		}
		
		return typedQuery.getResultList();
		
	}
	
}
