package com.dht.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dht.HibernateUtil;
import com.dht.pojo.Product;

public class ProductService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public List<Product> getProducts(String kw) {
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			query.select(root);

			if (kw != null && !kw.isEmpty()) {
				String p = String.format("%%%s%%", kw);
				Predicate p1 = builder.like(root.get("name").as(String.class), p);
				Predicate p2 = builder.like(root.get("description").as(String.class), p);

				query = query.where(builder.or(p1, p2));
			}

			return session.createQuery(query).getResultList();
		}
	}

	public boolean addOrSaveProduct(Product product) {
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.saveOrUpdate(product);
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}
		return true;
	}
	
	public boolean deleteProduct(Product p) { // p phải ở trạng thái persistent
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.delete(p);
				session.getTransaction().commit();
			}catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}
		
		return true;
	}
	
	public Product getProductById(int productId) {
		try (Session session = factory.openSession()) {
			return session.get(Product.class, productId);
		}
		
	}
}
