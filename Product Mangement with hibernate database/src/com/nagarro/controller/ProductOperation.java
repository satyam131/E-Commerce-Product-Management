package com.nagarro.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.EmailContainer;
import com.nagarro.model.Product;

/**
 * Servlet implementation class ProductOperation
 */
@MultipartConfig
public class ProductOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductOperation() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value = request.getParameter("operation");
		if (value.trim().equals("logout")) {
			response.sendRedirect("login.jsp");

		} else if (value.trim().equals("prodAdder")) {
			System.out.println("Value" + value);
			Session session = connection.getSession();
			Transaction transaction = session.beginTransaction();
			String userNameOrEmail = EmailContainer.getEmail();
			String title = request.getParameter("title");
			String quantity = request.getParameter("quantity");
			String size = request.getParameter("size");
			Product p = new Product();
			Part part = request.getPart("uploadedImage");
			String imageName = request.getParameter("image");
			String imagePath = request.getRealPath("img") + "\\" + imageName;
			InputStream fileInput = part.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(imagePath);
			byte data[] = new byte[fileInput.available()];
			fileInput.read(data);
			outputStream.write(data);
			Product detail = new Product(userNameOrEmail, title, quantity, size, imageName);
			session.save(detail);
			response.sendRedirect("product.jsp");
			transaction.commit();
		}
	}

}
