package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.UserDetail;

/**
 * Servlet implementation class SignupHandler
 */
public class SignupHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupHandler() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String username = request.getParameter("username");
		String email = request.getParameter("useremail");
		String password = request.getParameter("userpassword");
		String cnfpassword = request.getParameter("confirmpassword");
		signupAuthentication(username, email, password, cnfpassword, httpSession, response);
	}

	public void signupAuthentication(String username, String email, String password, String cnfpassword,
			HttpSession httpSession, HttpServletResponse response) throws IOException {
		System.out.println("Connection...." + connection);
		Session session = connection.getSession();
		Transaction transaction = session.beginTransaction();
		if (checkUserNameAndMail(session, username, email)) {
			httpSession.setAttribute("message", "Account already exists you can login to your account !!");
		} else if (checkUserName(session, username)) {
			httpSession.setAttribute("message", "username already exist !!");
		} else if (checkMail(session, email)) {
			httpSession.setAttribute("message", "Email already linked with other user !!");
		} else if (password.equals(cnfpassword) == false) {
			httpSession.setAttribute("message", "Confirm Password doesn't match with Password !!");
		} else {
			UserDetail detail = new UserDetail(username, email, password);
			System.out.println(detail);
			session.save(detail);
			httpSession.setAttribute("message", "Account created Successfully !!");
		}
		transaction.commit();
		response.sendRedirect("signup.jsp");
	}

	public boolean checkUserNameAndMail(Session session, String username, String email) {
		String hquery = "from UserDetail where name=:username and email=:email";
		Query query = session.createQuery(hquery);
		query.setParameter("username", username);
		query.setParameter("email", email);
		List<UserDetail> list = query.list();
		if (list.size() > 0)
			return true;
		return false;
	}

	public boolean checkUserName(Session session, String username) {
		String hquery = "from UserDetail where name=:username";
		Query query = session.createQuery(hquery);
		query.setParameter("username", username);
		List<UserDetail> list = query.list();
		if (list.size() > 0)
			return true;
		return false;
	}

	public boolean checkMail(Session session, String email) {
		String hquery = "from UserDetail where email=:email";
		Query query = session.createQuery(hquery);
		query.setParameter("email", email);
		List<UserDetail> list = query.list();
		if (list.size() > 0)
			return true;
		return false;
	}

}
