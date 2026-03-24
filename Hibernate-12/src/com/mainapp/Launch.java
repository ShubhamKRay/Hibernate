package com.mainapp;

import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;

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
		
		String sql = "delete from xemployee where id=:id";
		
		NativeQuery nativeQuery = session.createNativeQuery(sql);
		
		nativeQuery.setParameter("id", 1991);
		nativeQuery.executeUpdate();
		System.out.println("DATA DELETED");
		transaction.commit();

	}

	private static void update(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		String sql = "update xemployee set salary=:salary where id=:id";

		NativeQuery nativeQuery = session.createNativeQuery(sql);

		nativeQuery.setParameter("salary", 987987);
		nativeQuery.setParameter("id", 1991);
		nativeQuery.executeUpdate();
		System.out.println("DATA UPDATED");
		transaction.commit();

	}

	private static void read(Session session) {

		String sql = "select * from xemployee";

		NativeQuery nativeQuery = session.createNativeQuery(sql);
		nativeQuery.addEntity(Employee.class);
		List<Employee> list = nativeQuery.getResultList();

		for (Employee emp : list)

			System.out.println(emp);
	}

	private static void insert(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		String sql = "insert into xemployee(id,name,address,salary) values(:id, :name, :address, :salary)";

		NativeQuery nativeQuery = session.createNativeQuery(sql);

		nativeQuery.setParameter("id", 1991);
		nativeQuery.setParameter("name", "namex");
		nativeQuery.setParameter("address", "addressx");
		nativeQuery.setParameter("salary", 123456);
		nativeQuery.executeUpdate();
		System.out.println("DATA INSERTED");
		transaction.commit();

	}

}
