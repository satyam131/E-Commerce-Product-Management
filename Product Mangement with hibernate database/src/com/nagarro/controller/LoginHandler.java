package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.EmailContainer;
import com.nagarro.model.UserDetail;

/**
 * Servlet implementation class LoginHandler
 */
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	DatabaseConnectionProvider connection;

	public void init(ServletConfig config) throws ServletException {
		connection = new DatabaseConnectionProvider();
		connection.createConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session=request.getSession();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession htttpSession = request.getSession();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		loginAuthentication(userName, password, request, response, htttpSession);
	}

	public void loginAuthentication(String username, String password, HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws ServletException, IOException {
		Session session = connection.getSession();
		EmailContainer.setEmail(username);
		// String hquery ="from UserDetail";
		String hquery = "from UserDetail where (name=:username or email=:username) and password=:password";
		Query query = session.createQuery(hquery);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<UserDetail> list = query.list();
		if (list.size() > 0) {
			response.sendRedirect("product.jsp");
		} else {
			httpSession.setAttribute("message", "Wrong email or password");
			response.sendRedirect("login.jsp");
		}
	}

}
