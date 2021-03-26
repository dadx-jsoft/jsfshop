package com.dht.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.service.CategoryService;
import com.dht.service.ManufacturerService;

@ManagedBean
@Named
@SessionScoped
public class CateBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static CategoryService cateService = new CategoryService();
	private final static ManufacturerService manuService = new ManufacturerService();
	
	public CateBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Category> getCategories() {
		return cateService.getCategories();
	}
	
	public List<Manufacturer> getManufacturers(){
		return manuService.getManufacturers();
	}
	
}
