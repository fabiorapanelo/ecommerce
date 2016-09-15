package com.fabiorapanelo;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@ApplicationScoped
public class HibernateSessionFactory {

	private SessionFactory sessionFactory;

	public HibernateSessionFactory() {

		//Configure hibernate.cfg.xml
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session openSessionAndBeginTransaction() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		return session;
	}
}
