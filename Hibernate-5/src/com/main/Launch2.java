package com.main;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.entity.Employee;

public class Launch2 {

	// JPQL	
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config");
		EntityManager em = emf.createEntityManager();

//		insert(em);
//		read(em);
//		update(em);
		delete(em);

		em.close();
		emf.close();

	}

	private static void delete(EntityManager em) {
		
//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
//		 
//		 String sql = "delete from Employee where eid>=:eid"; 
//		 Query query = em.createQuery(sql); 
//		 query.setParameter("eid", 900);
//		 
//		 query.executeUpdate();
//		 
//		 transaction.commit();
//		 System.out.println("BULK DATA DELETED"); 
//		 em.close();
		
		
		
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query query = em.createNamedQuery("deleteJPQL");
		query.setParameter("eid", 13);
		
		query.executeUpdate();
		
		transaction.commit();
		System.out.println("BULK DATA DELETED");
		em.close();
	}

	private static void update(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		String jpql = "update Employee set esalary=:esalary where eid>=:eid";
		Query query = em.createQuery(jpql);
		query.setParameter("esalary", 98765);
		query.setParameter("eid", 900);
		
		query.executeUpdate();
		
		transaction.commit();
		System.out.println("BULK DATA UPDATED");
		em.close();
	}
	
	private static void read(EntityManager em) {
		
		
		String jpql = "select e from Employee e"; //COMPULSARY ALIASING
		Query query = em.createQuery(jpql,Employee.class);
		List<Employee> list = query.getResultList();
		
		for(Employee e : list) {
			System.out.println(e);
		}
		
		em.close();
		
	}


	private static void insert(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee(900+i, "jpqlname", "jpqladdr", 1000);
			em.persist(employee);
		
		if(i%5==0) {
			em.flush(); //CACHE MEMORY---------->DATABASE HIT(WAIT FOR CHANGE)
		}
		}
		transaction.commit();
		em.close();
		System.out.println("BULK DATA INSERTED");
	}
}
