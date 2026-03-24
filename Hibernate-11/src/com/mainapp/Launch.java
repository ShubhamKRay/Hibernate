package com.mainapp;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import com.entity.Employee;

public class Launch {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/myhibernate");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "Kumar@123");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");

		properties.put(Environment.HBM2DDL_AUTO, "update");
		properties.put(Environment.SHOW_SQL, "true");

		Configuration configuration = new Configuration();
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Employee.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		StatelessSession session = sessionFactory.openStatelessSession();

//		insert(session);
//		read(session);
//		update(session);
		delete(session);

		session.close();
		sessionFactory.close();

	}

	private static void delete(StatelessSession session) {

		Transaction transaction = session.getTransaction();

		Employee employee = (Employee) session.get(Employee.class, 1678);

		if (employee != null) {

			transaction.begin();
			session.delete(employee);
			System.out.println("DATA DELETED");
			transaction.commit();
		} else {
			System.out.println("DATA NOT FOUND");
		}
	}

	
	private static void update(StatelessSession session) {

		Transaction transaction = session.getTransaction();

		transaction.begin();

		Employee employee = (Employee) session.get(Employee.class, 11);

		if (employee != null) {
			
			employee.setEsalary(909090);
			session.update(employee);
			System.out.println("DATA UPDATED");
			transaction.commit();
		} else {
			System.out.println("DATA NOT FOUND");
		}
	}

	
	private static void read(StatelessSession session) {
		
		Employee employee1 = (Employee) session.get(Employee.class, 11);
		System.out.println(employee1); // CACHED

		Employee employee2 = (Employee) session.get(Employee.class, 1676);
		System.out.println(employee2); // CACHED

		Employee employee11 = (Employee) session.get(Employee.class, 11);
		System.out.println(employee11);
		
		Employee employee22 = (Employee) session.get(Employee.class, 1676);
		System.out.println(employee22);
	}

	
	
	private static void insert(StatelessSession session) {

		System.out.println(session);

		Employee employee = new Employee(1678, "RS", "address", 1000);
		Transaction transaction = session.getTransaction();

		transaction.begin();

		session.insert(employee);

		transaction.commit();
		System.out.println("DATA INSERTED");
	}

}
