package com.mainapp;

import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import com.entity.Employee;
import com.entity.EmployeeTest;

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
		configuration.addAnnotatedClass(EmployeeTest.class);

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

		String hql = "delete from Employee where eid=:eid";
		Query query = session.createQuery(hql);
		query.setParameter("eid", 901);
		query.executeUpdate();
		System.out.println("DATA DELETED");
		transaction.commit();

	}

	private static void update(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		String hql = "update Employee set esalary=:esalary where eid=:eid";

		Query query = session.createQuery(hql);
		query.setParameter("esalary", 8080);
		query.setParameter("eid", 901);

		query.executeUpdate();
		System.out.println("DATA UPDATED");
		transaction.commit();

	}

	private static void read(Session session) {

		String hql = "from Employee";

		Query query = session.createQuery(hql);
		List<Employee> list = query.list();

		for (Employee e : list) {
			System.out.println(e);
		}

	}

	// copy
	private static void insert(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		String hql = "insert into EmployeeTest(eid,ename,eaddress,esalary) select eid,ename,eaddress,esalary from Employee";

		Query query = session.createQuery(hql);
		query.executeUpdate();
		System.out.println("DATA COPIED....");
		transaction.commit();

	}

}
