package com.dht.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import com.dht.pojo.Product;
import com.dht.service.ProductService;

@ManagedBean
@Named("productBean")
@SessionScoped
//@RequestScoped
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final static ProductService productSevice = new ProductService();

	public ProductBean() {
	}

	public List<Product> getProducts() {
		return productSevice.getProducts(null);
	}
}
