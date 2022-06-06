package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.PostoEletrico;
import br.com.fiap.model.PostoEletrico;

public class PostoEletricoDao {
	
	EntityManager manager = JpaManager.getManager();
	
	public void inserir(PostoEletrico posto) {
			
			manager.getTransaction().begin();
			manager.persist(posto);
			manager.getTransaction().commit();
	}
	
	public List<PostoEletrico> listarTodos() {
		TypedQuery<PostoEletrico> query = 
				manager.createQuery("SELECT p FROM PostoEletrico p", PostoEletrico.class);
		return query.getResultList();

	}
	public List<PostoEletrico> ordenarTodos() {
		TypedQuery<PostoEletrico> query = 
				manager.createQuery("SELECT p FROM PostoEletrico p ORDER BY p.estado", PostoEletrico.class);
			return query.getResultList();
		}	
	}



