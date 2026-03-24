package com.mainapp;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.entity.Cricketer;
import com.entity.Footballer;
import com.entity.Player;

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

		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Cricketer.class);
		configuration.addAnnotatedClass(Footballer.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

//		insert(session);
//		readSQL(session);
		readHQL(session);

		session.close();
		sessionFactory.close();

	}

	private static void readSQL(Session session) {
		String sql = "select * from cricketer UNION ALL select * from Footballer";
		NativeQuery nativeQuery = session.createNativeQuery(sql);
		List<Object[]> list = nativeQuery.getResultList();
		
		for (Object[] orr : list) {
			for (Object o : orr) {
				System.out.print(o + " ");
			}
			System.out.println();
		}
	}

	// POLYMORPHIC QUERY
	private static void readHQL(Session session) {

		String hql = "from Player";

		Query query = session.createQuery(hql);
		List<Player> list = query.list();

		for (Player p : list) {
			if (p instanceof Cricketer) {
				System.out.println(p);
			} else {
				System.out.println(p);
			}

		}

	}

	private static void insert(Session session) {

		Transaction transaction = session.getTransaction();
		transaction.begin();

		Cricketer cricketer = new Cricketer(111, "Virat kohli", 1000, "batsman");
		Footballer footballer = new Footballer(121, "ronaldo", 70, "gk");

		session.save(cricketer);
		session.save(footballer);

		transaction.commit();
	}

}
