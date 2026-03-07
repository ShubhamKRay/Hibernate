package com.mainapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Launch {
	public static void main(String[] args) {
		
		//Create EntityManagerFactory using the persistence unit name
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJpaUnit");
		
		//Get EntityManager to interact with the database
		EntityManager em = emf.createEntityManager();
		
		System.out.println(em);
		System.out.println("JPA Connection Established Successfully!");
	
		//Close resources
		em.close();
		emf.close();
	}
}
