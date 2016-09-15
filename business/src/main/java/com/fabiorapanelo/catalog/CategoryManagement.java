package com.fabiorapanelo.catalog;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.HibernateSessionFactory;

@ApplicationScoped
public class CategoryManagement {

	@Inject
	private HibernateSessionFactory sessionFactory;
	
	public List<Category> getCategories() {

		Session session = sessionFactory.openSessionAndBeginTransaction();

		TypedQuery<Category> query = session.createQuery("from Category", Category.class);
		List<Category> categories = query.getResultList();

		session.close();

		return categories;
	}

	public void createCategory(Category category) {

		Session session = sessionFactory.openSessionAndBeginTransaction();
		session.save(category);
		session.getTransaction().commit();
		session.close();

	}

	public Category getCategory(int id) {

		Session session = sessionFactory.openSessionAndBeginTransaction();

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
