package com.fabiorapanelo.auth;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.SessionFactorySingleton;

public class UserManagement {
	
	public static void createUser(User user) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

	}
	
	public static User getUser(String email) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

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
	
	public static void updateUser(User user){
		
	}

}
