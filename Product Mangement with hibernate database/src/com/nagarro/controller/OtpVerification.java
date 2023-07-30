package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.model.SendMail;

/**
 * Servlet implementation class OtpVerification
 */
public class OtpVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OtpVerification() {
		super();
		// TODO Auto-generated constructor stub
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
		String generatedOtp = SendMail.getOtp();
		String userFilledOtp = request.getParameter("otp");
		HttpSession httpSession = request.getSession();
		if (generatedOtp.equals(userFilledOtp)) {
			httpSession.setAttribute("message", "OTP Verified Successfully..");
			response.sendRedirect("passwordUpdater.jsp");
		} else {
			httpSession.setAttribute("message", "OTP doesn't match !!");
			response.sendRedirect("otpVerification.jsp");
		}
	}

}
