package com.dht.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dht.pojo.Payment;
import com.dht.pojo.PaymentDetail;
import com.dht.pojo.Product;
import com.dht.pojo.User;
import com.dht.service.PaymentService;
import com.dht.service.ProductService;

//@ManagedBean
@Named
@RequestScoped
public class PaymentBean {
	private static final ProductService productService = new ProductService();
	private static final PaymentService paymentService = new PaymentService();
	public PaymentBean() {
	}

	public String add() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (cart != null) {
			Payment payment = new Payment();
			payment.setCreatedDate(new Date());
			payment.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
			
			List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
			List<Map<String, Object>> kq = new ArrayList<>();
			for (Object o : cart.values()) {
				Map<String, Object> d = (Map<String, Object>) o;
				Product product = productService.getProductById((int) d.get("productId"));
				PaymentDetail paymentDetail = new PaymentDetail();
				paymentDetail.setProduct(product);
				paymentDetail.setPayment(payment);
				paymentDetail.setPrice(product.getPrice());
				paymentDetail.setCount((int) d.get("count"));
				
				paymentDetails.add(paymentDetail);
			}
			if(paymentService.addPayment(payment, paymentDetails) == true) {
				// clean cart
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cart");
				return "index?faces-redirect=true";
			}
		}
		
		return "payment";
	}
}
