package com.fabiorapanelo.catalog;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.SessionFactorySingleton;

public class CategoryManagement {

	public static List<Category> getCategories() {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Category> query = session.createQuery("from Category", Category.class);
		List<Category> categories = query.getResultList();

		session.close();

		return categories;
	}

	public static void createCategory(Category category) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		session.save(category);
		session.getTransaction().commit();
		session.close();

	}

	public static Category getCategory(int id) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Category> query = session.createQuery("from Category where CATEGORY_ID = :id ", Category.class);
		query.setParameter("id", id);

		Category category = null;
		try {
			category = query.getSingleResult();
		} catch (NoResultException nre) {

		} finally {
			session.close();
		}

		return category;
	}

}
