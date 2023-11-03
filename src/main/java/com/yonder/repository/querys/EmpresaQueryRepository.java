package com.yonder.repository.querys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yonder.model.Empresa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EmpresaQueryRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Empresa> empresaQuery(String nome, String cnpj){
		
		StringBuilder query = new StringBuilder("from Empresa p where ");
		if(nome != null) {
			
			query.append("p.nome  = :nome ");
		}
		if(cnpj != null) {
			if(query.length() != 21) {
				query.append("and ");
			}
			query.append("p.cnpj  = :cnpj ");
		}
		
		
		TypedQuery<Empresa> typedQuery = em.createQuery(query.toString(), Empresa.class);
		if(nome != null) {
			typedQuery.setParameter("nome", nome);
		}
		if(cnpj != null) {
			typedQuery.setParameter("cnpj", cnpj);
		}
		
		
		return typedQuery.getResultList();
		
	}
}
