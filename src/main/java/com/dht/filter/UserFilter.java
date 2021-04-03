package com.dht.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dht.pojo.User;

@WebFilter(urlPatterns = { "/faces/products.xhtml" })
public class UserFilter implements Filter {
	private HttpServletRequest httpRequest;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession();
		User u = (User) session.getAttribute("user");
		if (u != null) {
			if (!u.getuRole().equals("ADMIN")) {
				String home = "/faces/index.xhtml";
				RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(home);
				dispatcher.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			String loginPage = "/faces/login.xhtml";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		}

	}

	@Override
	public void destroy() {

	}

}
