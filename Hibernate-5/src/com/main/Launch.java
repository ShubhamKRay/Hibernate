package com.main;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.entity.Employee;

public class Launch {

	// NATIVE SQL
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("config");
		EntityManager em = emf.createEntityManager();

		//insert(em);
		//read(em);
		//update(em);
		delete(em);

		em.close();
		emf.close();

	}

	private static void delete(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query query = em.createNamedQuery("deleteSQL");
		query.setParameter("id", 117);
		
		query.executeUpdate();
		
		transaction.commit();
		System.out.println("BULK DATA DELETED");
		em.close();
	}

	private static void read(EntityManager em) {
	
//		String sql = "select * from xemployee";
//		Query query = em.createNativeQuery(sql);
//		List<Object[]> list = query.getResultList();
//		
//	    for(Object[] orr : list) {
//	    	for(Object o : orr) {
//	    		System.out.println(o+" ");
//	    	}
//	    	System.out.println();
//	    }
		
		
		
		String sql = "select * from xemployee";
		Query query = em.createNativeQuery(sql,Employee.class);
		List<Employee> list = query.getResultList();
		
		for(Employee e : list) {
			System.out.println(e);
		}
		
		em.close();
		
	}

	private static void update(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		String sql = "update xemployee set salary=? where id>=?";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, 98989898);
		query.setParameter(2, 12347);
		
		query.executeUpdate();
		
		transaction.commit();
		System.out.println("BULK DATA UPDATED");
		em.close();
	}

	private static void insert(EntityManager em) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		String sql = "insert into xemployee(id,name,address,salary) values(?,?,?,?)";

		for (int i = 1; i <= 10; i++) {
			Query nativeQuery = em.createNativeQuery(sql);
			nativeQuery.setParameter(1, 12345 + i);
			nativeQuery.setParameter(2, "xyzijkl");
			nativeQuery.setParameter(3, "adddrrr");
			nativeQuery.setParameter(4, 676754 + i);

			nativeQuery.executeUpdate();
		}
		transaction.commit();
		em.close();
		System.out.println("BULK DATA INSERTED");
	}
}
