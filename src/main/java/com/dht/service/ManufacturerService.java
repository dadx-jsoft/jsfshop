package com.dht.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dht.HibernateUtil;
import com.dht.pojo.Manufacturer;

public class ManufacturerService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public List<Manufacturer> getManufacturers() {
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Manufacturer> query = builder.createQuery(Manufacturer.class);
			Root<Manufacturer> root = query.from(Manufacturer.class);
			query.select(root);

			return session.createQuery(query).getResultList();

		}
	}
}
