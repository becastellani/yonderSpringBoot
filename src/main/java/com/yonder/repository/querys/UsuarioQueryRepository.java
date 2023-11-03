package com.yonder.repository.querys;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.yonder.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@Repository
public class UsuarioQueryRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Usuario> usuarioQuery(String nome, String cpf){
		
		StringBuilder query = new StringBuilder("from Usuario p where ");
		if(nome != null) {
			
			query.append("p.nome  = :nome ");
		}
		if(cpf != null) {
			if(query.length() != 21) {
				query.append("and ");
			}
			query.append("p.cpf  = :cpf ");
		}
		
		
		TypedQuery<Usuario> typedQuery = em.createQuery(query.toString(), Usuario.class);
		if(nome != null) {
			typedQuery.setParameter("nome", nome);
		}
		if(cpf != null) {
			typedQuery.setParameter("cpf", cpf);
		}
		
		
		return typedQuery.getResultList();
		
	}
}
