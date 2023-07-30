package com.nagarro.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import com.nagarro.model.UserDetail;

/**
 * Servlet implementation class PasswordHandler
 */
public class PasswordHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordHandler() {
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
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("message", null);
		String userNameOrEmail = request.getParameter("userNameOrEmail");
		Session session = connection.getSession();
		String hql = "from UserDetail where name=:userNameOrEmail or email=:userNameOrEmail";
		Query query = session.createQuery(hql);
		query.setParameter("userNameOrEmail", userNameOrEmail);
		List<UserDetail> list = query.list();
		if (list.size() > 0) {
			String email = list.get(0).getEmail();
			int code = ThreadLocalRandom.current().nextInt(100000, 999999);
			httpSession.setAttribute("message", "OTP Sent Successfully.....");
			SendMail.send(email, "Otp Verification ", String.valueOf(code), "saurabhtrigunayat09@gmail.com",
					"fjenpwwcikzrljud");
			response.sendRedirect("otpVerification.jsp");
		} else {
			response.sendRedirect("forgotPassword.jsp");
			httpSession.setAttribute("message", "No account linked with given email !!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
