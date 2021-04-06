package com.dht.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.pojo.Product;
import com.dht.service.ProductService;

//@ManagedBean
@Named("productBean")
@SessionScoped
//@RequestScoped
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static ProductService productSevice = new ProductService();

	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private Category category;
	private Set<Manufacturer> manufacturers;
	private Part imgFile;

	public static ProductService getProductsevice() {
		return productSevice;
	}

	public ProductBean() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			String productId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("product_id");
			if (productId != null && !productId.isEmpty()) {
				Product p = productSevice.getProductById(Integer.parseInt(productId));
				this.productId = p.getId();
				this.name = p.getName();
				this.description = p.getDescription();
				this.price = p.getPrice();
				this.category = p.getCategory();
				this.manufacturers = p.getManufacturers();
			}
		}

	}

	public List<Product> getProducts() {
		return productSevice.getProducts(null);
	}

	private void uploadFile() throws IOException {
//		String path = FacesContext.getCurrentInstance().getExternalContext()
//									.getRealPath("/resources/images/upload")
//									+"/"+this.imgFile.getSubmittedFileName();
		String path = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadPath") + "/"
				+ this.imgFile.getSubmittedFileName();
		try (InputStream in = this.imgFile.getInputStream(); FileOutputStream out = new FileOutputStream(path)) {
			byte[] b = new byte[1024];
			int byteRead;

			while ((byteRead = in.read(b)) != -1) {
				out.write(b, 0, byteRead);
			}
		}
	}

	public String addProduct() {
		
		Product p;
		if(this.productId>0) {
			p = productSevice.getProductById(this.productId);
		}else {
			p = new Product();
		}
		p.setName(this.name);
		p.setDescription(this.description);
		p.setPrice(this.price);
		p.setCategory(this.category);
		p.setManufacturers(this.manufacturers);

		try {
			if (this.imgFile != null) {
				this.uploadFile();
				p.setImage("upload/" + this.imgFile.getSubmittedFileName());
			}

			if (productSevice.addOrSaveProduct(p)) {
				return "product-list?faces-redirect=true";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "products";
	}

	public String deleteProduct(Product p) throws Exception {
		if (productSevice.deleteProduct(p))
			return "successful";
		throw new Exception("Something wrong !");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public Part getImgFile() {
		return imgFile;
	}

	public void setImgFile(Part imgFile) {
		this.imgFile = imgFile;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
