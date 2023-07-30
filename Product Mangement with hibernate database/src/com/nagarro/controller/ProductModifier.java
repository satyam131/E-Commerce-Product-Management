package com.nagarro.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.EmailContainer;
import com.nagarro.model.Product;
import com.nagarro.model.SendMail;

/**
 * Servlet implementation class ProductModifier
 */
@MultipartConfig
public class ProductModifier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductModifier() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product sessionData=(Product) request.getSession().getAttribute("Object");
		String title=request.getParameter("title");
		String quantity=request.getParameter("quantity");
		String size=request.getParameter("size");
		String imageName=request.getParameter("imageName");
		int Id=sessionData.getId();
		Part part = request.getPart("image");
		HttpSession httpSession = request.getSession();
		Session session = connection.getSession();
		Transaction transaction=session.beginTransaction();
		if(sessionData.getImage().equals(imageName)==false)
		{
		   String imagePath = request.getRealPath("img") + "\\" + imageName;
		InputStream fileInput = part.getInputStream();
		FileOutputStream outputStream = new FileOutputStream(imagePath);
		byte data[] = new byte[fileInput.available()];
		fileInput.read(data);
		outputStream.write(data);
		}
		String hquery = "update Product set title=:title,quantity=:quantity,size=:size,image=:imageName where id=:Id";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(hquery);
		query.setParameter("title", title);
		query.setParameter("quantity", quantity);
		query.setParameter("size", size);
		query.setParameter("imageName", imageName);
		query.setParameter("Id", Id);
		query.executeUpdate();
		httpSession.removeAttribute("Object");
		transaction.commit();
		httpSession.setAttribute("message","Product Successfully updated !!!");
		httpSession.setAttribute("Object",sessionData);
		sessionData.setTitle(title);
		sessionData.setImage(imageName);
		sessionData.setSize(size);
		sessionData.setQuantity(quantity);
		response.sendRedirect("editIcon.jsp");
	}

}
