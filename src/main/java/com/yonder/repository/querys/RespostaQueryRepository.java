package com.yonder.repository.querys;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.yonder.model.Resposta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@Repository
public class RespostaQueryRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Resposta> respostaQuery(String texto,  String correta){
		
		StringBuilder query = new StringBuilder("from Resposta p where ");
		if(texto != null) {
			
			query.append("p.texto  = :texto ");
		}
		
		if(correta != null && (correta.equals("true") || correta.equals("false"))) {
			if(query.length() != 22) {
				query.append("and ");
			}
			query.append("p.correta  = :correta");
		}
		
		TypedQuery<Resposta> typedQuery = em.createQuery(query.toString(), Resposta.class);
		if(texto != null) {
			typedQuery.setParameter("texto", texto);
		}
		if(correta != null && (correta.equals("true") || correta.equals("false"))) {
			if(correta.equals("true")) {
				System.out.println(correta);
				typedQuery.setParameter("correta", true);
			}else if(correta.equals("false")){
				typedQuery.setParameter("correta", false);
			}
		}
		
		return typedQuery.getResultList();
		
	}
}
