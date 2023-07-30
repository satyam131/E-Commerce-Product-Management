package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.EmailContainer;
import com.nagarro.model.Product;

/**
 * Servlet implementation class EditIconHandler
 */
public class EditIconHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditIconHandler() {
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int Id = Integer.parseInt(id);
		HttpSession httpSession=request.getSession();
		Session session = connection.getSession();
		// String hquery ="from UserDetail";
		String hquery = "from Product where id=:Id";
		Query query = session.createQuery(hquery);
		query.setParameter("Id", Id);
		List<Product> product=query.list();
		Product data=product.get(0);
		httpSession.setAttribute("Object", data);
		response.sendRedirect("editIcon.jsp");
	}

}
