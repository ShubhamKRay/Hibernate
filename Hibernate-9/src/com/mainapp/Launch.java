package com.mainapp;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session session = sessionFactory.openSession();

//		 insert(session);
//		 read(session);
//		 update(session);
		 delete(session);

		session.close();
		sessionFactory.close();
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
