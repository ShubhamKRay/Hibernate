package com.mainapp;

import java.time.LocalDateTime;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.entity.Account;
import com.entity.Car;
import com.entity.Company;
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

//		insert(session);
//		read(session);
//		update(session);
		delete(session);

		session.close();
		sessionFactory.close();

	}

	private static void delete(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		Employee employee = session.get(Employee.class, "eid12343");

		if (employee != null) {
			session.delete(employee);
			System.out.println("DATA DELETED");
		} else {
			System.out.println("EMPLOYEE DATA NOT FOUND");
		}

		transaction.commit();
	}

	private static void update(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		Employee employee = session.get(Employee.class, "eid12343");
		employee.setEmployeename("Ray Shubham");

		session.update(employee);

		transaction.commit();
		System.out.println("DATA UPDATED");
	}

	private static void read(Session session) {

		Employee employee = session.get(Employee.class, "eid12343");
		System.out.println(employee);
	}

	private static void insert(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		Account account = new Account("1212ABCD", "SHUBHAMACC", "SHUBHACCADDR", "SBIBANK", "SBIN0001234");
		Car car = new Car("BR04L1020", "HEROMODEL", LocalDateTime.now(), 1000.0);
		Company company = new Company("reg1234", "IBMNAME", "policy", account);
		Employee employee = new Employee("eid12343", "SHUBHAM", company, car);

		session.save(employee);

		transaction.commit();
	}

}
