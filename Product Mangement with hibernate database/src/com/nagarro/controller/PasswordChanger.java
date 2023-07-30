package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.SendMail;

/**
 * Servlet implementation class PasswordChanger
 */
public class PasswordChanger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordChanger() {
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
		String newPassword = request.getParameter("newpassword");
		String confirmNewPassword = request.getParameter("confirmnewpassword");
		HttpSession httpSession = request.getSession();
		if (newPassword.equals(confirmNewPassword)) {
			Session session = connection.getSession();
			Transaction transaction = session.beginTransaction();
			String email = SendMail.getEmail();
			String hquery = "update UserDetail set password=:newPassword where email=:email";
			Query query = session.createQuery(hquery);
			query.setParameter("email", email);
			query.setParameter("newPassword", newPassword);
			int row = query.executeUpdate();
			transaction.commit();
			httpSession.setAttribute("message", "Password Updated Successfully");
		} else {
			httpSession.setAttribute("message", "Password Updated Successfully");
		}
		response.sendRedirect("passwordUpdater.jsp");
	}

}
