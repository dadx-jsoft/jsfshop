package com.dht;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.pojo.Product;

public class HibernateUtil {

	private final static SessionFactory FACTORY;

	static {
		Configuration conf = new Configuration();

		// Hibernate settings equivalent to hibernate.cfg.xml's properties
		Properties props = new Properties();
//		props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//		props.put(Environment.URL, "jdbc:mysql://localhost:3306/jsfsaledb?useSSL=false");
//		props.put(Environment.USER, "root");
//		props.put(Environment.PASS, "root");
//		props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		
		props.put(Environment.DRIVER, "org.postgresql.Driver");
		props.put(Environment.URL, "jdbc:postgresql://localhost:5433/jsfsaledb?useSSL=false");
		props.put(Environment.USER, "postgres");
		props.put(Environment.PASS, "root");
		props.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

		props.put(Environment.SHOW_SQL, "true");
//
//		props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//		props.put(Environment.HBM2DDL_AUTO, "create-drop");

		conf.setProperties(props);

		conf.addAnnotatedClass(Category.class);
		conf.addAnnotatedClass(Product.class);
		conf.addAnnotatedClass(Manufacturer.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();

		FACTORY = conf.buildSessionFactory(serviceRegistry);
	}
	public final static SessionFactory getFactory() {
		return FACTORY;
	}
}
