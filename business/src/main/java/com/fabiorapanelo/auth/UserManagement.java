package com.fabiorapanelo.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.HibernateSessionFactory;

@ApplicationScoped
public class UserManagement {
	
	@Inject
	private HibernateSessionFactory sessionFactory;
	
	public void createUser(User user) {

		Session session = sessionFactory.openSessionAndBeginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

	}
	
	public User getUser(String email) {

		Session session = sessionFactory.openSessionAndBeginTransaction();

		TypedQuery<User> query = session.createQuery("from User where email = :email ", User.class);
		query.setParameter("email", email);

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {

		} finally {
			session.close();
		}

		return user;
	}
	
	public void updateUser(User user){
		
	}

}
