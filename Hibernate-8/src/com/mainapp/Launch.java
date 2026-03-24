package com.mainapp;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.entity.Employee;

public class Launch {

	public static void main(String[] args) {

		//Load configuration from hibernate.cfg.xml
		Configuration configuration = new Configuration();
		configuration.configure();

		//SessionFactory : SETUP READY (heavy weight object (pool, mappings, Session))
		SessionFactory sf = configuration.buildSessionFactory();
		
		//To interact with DB we use session
		Session session = sf.openSession();
		
		System.out.println(session);

//		insert(session);
//		read(session);
//		update(session);
		delete(session);

		session.close();
		sf.close();

	}

	private static void delete(Session session) {

		Transaction transaction = session.getTransaction();
		Employee employee = session.get(Employee.class, 11);

		if (employee != null) {
			transaction.begin();
			session.delete(employee);
			System.out.println("DATA DELETED");
			transaction.commit();

		} else {
			System.out.println("DATA NOT FOUND");
		}

	}

	private static void update(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();
		Employee employee = session.get(Employee.class, 11);

		if (employee != null) {
			employee.setEsalary(9000);
			session.update(employee);
			System.out.println("DATA UPDATED");

		} else {
			System.out.println("DATA NOT FOUND");
		}
		transaction.commit();

	}

	private static void read(Session session) {

		Employee employee = session.get(Employee.class, 11);
		System.out.println(employee);
	}

	private static void insert(Session session) {

		System.out.println(session);
		Employee employee = new Employee(11, "name", "address", 1000);
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(employee);
		transaction.commit();
		System.out.println("DATA INSERTED");
	}
}
