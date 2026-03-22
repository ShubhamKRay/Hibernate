package com.mainapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Launch {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure();
		
		SessionFactory sf = configuration.buildSessionFactory();
		
		Session session = sf.openSession();
		
		System.out.println(session);
	}
}
