package com.fabiorapanelo.catalog;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.fabiorapanelo.SessionFactorySingleton;

public class ProductManagement {

	public static List<Product> getProducts() {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Product> query = session.createQuery("from Product", Product.class);
		List<Product> products = query.getResultList();

		session.close();

		return products;
	}

	public static void createProduct(Product product) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();

	}

	public static Product getProduct(int id) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

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
