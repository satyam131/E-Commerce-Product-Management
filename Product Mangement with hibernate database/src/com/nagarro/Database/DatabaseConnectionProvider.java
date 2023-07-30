package com.nagarro.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseConnectionProvider {
	private Session session;

	public void createConnection() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		System.out.println("hii");
		SessionFactory factory = config.buildSessionFactory();
		session = factory.openSession();
	}

	public Session getSession() {
		return session;
	}
}
