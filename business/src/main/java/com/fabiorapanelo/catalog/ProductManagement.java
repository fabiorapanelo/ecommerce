package com.fabiorapanelo.catalog;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.HibernateSessionFactory;

@Named
@ApplicationScoped
public class ProductManagement {

	@Inject
	private HibernateSessionFactory sessionFactory;
	
	public List<Product> getProducts() {

		Session session = sessionFactory.openSessionAndBeginTransaction();

		TypedQuery<Product> query = session.createQuery("from Product", Product.class);
		List<Product> products = query.getResultList();

		session.close();

		return products;
	}

	public void createProduct(Product product) {

		Session session = sessionFactory.openSessionAndBeginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();

	}

	public Product getProduct(int id) {

		Session session = sessionFactory.openSessionAndBeginTransaction();

		TypedQuery<Product> query = session.createQuery("from Product where PRODUCT_ID = :id ", Product.class);
		query.setParameter("id", id);

		Product product = null;
		try {
			product = query.getSingleResult();
		} catch (NoResultException nre) {

		} finally {
			session.close();
		}

		return product;
	}

}
