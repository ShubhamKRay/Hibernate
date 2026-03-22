package com.mainapp;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Launch {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "Kumar@123");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");

		Configuration configuration = new Configuration();
		configuration.setProperties(properties);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		System.out.println(session);
	}
}
