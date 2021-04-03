package com.dht.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dht.HibernateUtil;
import com.dht.pojo.User;

public class UserService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public boolean addUser(User u) {
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();

				// băm md5
				u.setPassword(DigestUtils.md5Hex(u.getPassword()));
				session.saveOrUpdate(u);

				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				session.getTransaction().rollback();
			}
		}
		return false;
	}

	public User login(String username, String password) {
		password = DigestUtils.md5Hex(password);

		// Truy vấn csdl bằng CriteriaBuilder
		try (Session session = factory.openSession()) {
			CriteriaBuilder b = session.getCriteriaBuilder();
			CriteriaQuery<User> q = b.createQuery(User.class);
			Root<User> root = q.from(User.class);
			q.select(root);
			q.where(b.and(b.equal(root.get("username").as(String.class),username),
					b.equal(root.get("password").as(String.class), password)));
			return session.createQuery(q).getSingleResult();
		}
	}
}
