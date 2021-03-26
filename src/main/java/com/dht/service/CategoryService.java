package com.dht.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dht.HibernateUtil;
import com.dht.pojo.Category;

public class CategoryService {
	private final static SessionFactory factory = HibernateUtil.getFactory();
	
	public List<Category> getCategories(){
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Category> query = builder.createQuery(Category.class);
			Root<Category> root = query.from(Category.class);
			query.select(root);
			
			return session.createQuery(query).getResultList();
		} 
	}
}
