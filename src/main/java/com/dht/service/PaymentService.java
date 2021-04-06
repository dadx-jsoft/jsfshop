package com.dht.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dht.HibernateUtil;
import com.dht.pojo.Payment;
import com.dht.pojo.PaymentDetail;

public class PaymentService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public boolean addPayment(Payment payment, List<PaymentDetail> paymentDetails) {
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				
				session.save(payment);
				for (PaymentDetail paymentDetail : paymentDetails) {
					session.save(paymentDetail);
				}
				
				session.getTransaction().commit();

				return true;
			} catch (Exception e) {
				session.getTransaction().rollback();
			}
		}
		return false;
	}
}
