package br.com.fiap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaManager {
		
		private static EntityManager manager;
		private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PostoEletrico");

		public static EntityManager getManager() {
			if (manager == null) manager = factory.createEntityManager();
			return manager;
		}
	}

