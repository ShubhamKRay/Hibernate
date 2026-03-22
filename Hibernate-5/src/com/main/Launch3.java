package com.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.entity.Employee;

public class Launch3 {

	// Criteria API
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config");
		EntityManager em = emf.createEntityManager();

//		insert(em);
//		read(em);
//		update(em);
//		delete(em);

		em.close();
		emf.close();

	}

	private static void delete(EntityManager em) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<Employee> cd = criteriaBuilder.createCriteriaDelete(Employee.class);
		Root<Employee> root = cd.from(Employee.class); 
		
//		cd.where(criteriaBuilder.lessThan(root.get("eid"), 905));
		
		Query query = em.createQuery(cd);
		query.executeUpdate();
		
		transaction.commit(); 
		System.out.println("BULK DATA DELETED");
		em.close();

	}

	private static void update(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaUpdate<Employee> cu = criteriaBuilder.createCriteriaUpdate(Employee.class);
		Root<Employee> root = cu.from(Employee.class);

		cu.set("esalary", 6000); // set esalary=6000
		cu.where(criteriaBuilder.lessThan(root.get("eid"), 905)); // eid<905

		Query query = em.createQuery(cu);
		query.executeUpdate();

		transaction.commit();
		System.out.println("BULK DATA UPDATED");
		em.close();

	}

	private static void read(EntityManager em) {

		// select * from employee

//		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//		CriteriaQuery<Employee> cq = criteriaBuilder.createQuery(Employee.class); // READ CRITERIA
//
//		Root<Employee> from = cq.from(Employee.class); // from Employee
//		cq.select(from); // select * from Employee
//
//		TypedQuery<Employee> query = em.createQuery(cq);
//		List<Employee> list = query.getResultList();
//		
//		System.out.println(list);
//		
//		em.close();

		// select id name where name like 'j%' and id>905

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Employee> cq = criteriaBuilder.createQuery(Employee.class); // READ CRITERIA

		Root<Employee> root = cq.from(Employee.class); // from Employee

		Predicate p1 = criteriaBuilder.like(root.get("ename"), "j%"); // name like 'j%'
		Predicate p2 = criteriaBuilder.greaterThan(root.get("eid"), 905); // id>905

		cq.multiselect(root.get("eid"), root.get("ename")).where(criteriaBuilder.and(p1, p2));

		List<Employee> list = em.createQuery(cq).getResultList();
		System.out.println(list);

		em.close();

	}

	private static void insert(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee(900 + i, "jpqlname", "jpqladdr", 1000);
			em.persist(employee);

			if (i % 5 == 0) {
				em.flush(); // CACHE MEMORY---------->DATABASE HIT(WAIT FOR CHANGE)
			}
		}
		transaction.commit();
		em.close();
		System.out.println("BULK DATA INSERTED");
	}
}
