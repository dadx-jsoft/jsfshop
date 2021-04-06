package com.dht.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Transient;

import com.dht.pojo.User;
import com.dht.service.UserService;

//@ManagedBean
@Named
@RequestScoped
public class UserBean {
	private String name;
	private String email;
	private String username;
	private String password;
	@Transient // chỉ là thuộc tính dùng để validate
	private String confirmPassword;

	private static UserService userService = new UserService();

	public String register() {
		if (!this.password.isEmpty() && this.password.equals(this.confirmPassword)) {
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(password);
			u.setUsername(username);
			u.setuRole("USER");
			if (userService.addUser(u))
				return "login?faces-redirect=true";
		}
		return "register";
	}

	public String login() {
		User u = userService.login(username, password);
		if (u != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
			return "index?faces-redirect=true";
		}

		return "login";
	}
	
	public String checkLogin() {
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null)
			return "index?faces-redirect=true";
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}
	public UserBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
