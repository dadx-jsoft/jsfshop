package com.dht.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

//@ManagedBean
@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CartBean() {
	}

	@PostConstruct
	public void init() {
		Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.get("cart") == null) {
			sessionMap.put("cart", new HashMap<>());
		}
	}

	public List<Map<String, Object>> getCarts() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");

		List<Map<String, Object>> kq = new ArrayList<>();
		for (Object o : cart.values()) {
			Map<String, Object> d = (Map<String, Object>) o;
			kq.add(d);
		}

		return kq;
	}

	public void addItemToCart(int productId, String productName, BigDecimal price) {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (cart.get(productId) == null) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("productId", productId);
			data.put("productName", productName);
			data.put("productPrice", price);
			data.put("count", 1);

			cart.put(productId, data);
		} else {
			Map<String, Object> data = (Map<String, Object>) cart.get(productId);
			data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
		}
	}
	
	
	public int getSoLuongSanPham() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		int soLuongSanPham = 0;
		for (Object o : cart.values()) {
			Map<String, Object> d = (Map<String, Object>) o;
			soLuongSanPham += Integer.parseInt(d.get("count").toString());
		}
		return soLuongSanPham;
	}

}
