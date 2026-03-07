package com.mainapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entity.Employee;

public class Launch {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config");

		EntityManager em = emf.createEntityManager();

		// insert(em);
		// read(em);
		// update(em);
		 delete(em);

		em.close();
		emf.close();

	}

	private static void delete(EntityManager em) {

		Employee employee = em.find(Employee.class, 22);
		if (employee != null) {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(employee);

			transaction.commit();
			System.out.println("DATA DELETED SUCCESSFULLY");
		} else {
			System.out.println("DATA NOT FOUND");
		}
	}

	private static void update(EntityManager em) {

		Employee employee = em.find(Employee.class, 12);
		if (employee != null) {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			employee.setEsalary(6000);
			em.merge(employee);

			employee.setEaddress("delhi");
			em.merge(employee);

			transaction.commit();
			System.out.println("DATA UPDATED SUCCESSFULLY");
		} else {
			System.out.println("DATA NOT FOUND");
		}
	}

	private static void read(EntityManager em) {

		Employee employee = em.find(Employee.class, 12);
		System.out.println(employee);
	}

	private static void insert(EntityManager em) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Employee employee = new Employee(22, "shubh", "noida", 3000);
		em.persist(employee);

		transaction.commit();
		System.out.println("DATA INSERTED SUCCESSFULLY");
		System.out.println("Data Generated");

	}
}
