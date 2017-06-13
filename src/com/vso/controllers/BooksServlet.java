package com.vso.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vso.MySqlTest;
import com.vso.models.Book;

/**
 * Servlet implementation class BooksServlet
 */
@WebServlet(urlPatterns = { "/BooksServlet" })
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BooksServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Date today = new Date();
		out.println("<html>" + "<body>" + "<h1 align=center>HF\'s " + "Chapter1 Servlet</h1>" + "<br>" + today);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("txt/html");
		PrintWriter out = response.getWriter();
		MySqlTest database = new MySqlTest();
		out.println("Books selection advice<br> ");
		String bookName = request.getParameter("bookName");
		List<Book> books = database.getBookInfo(bookName);
		
		request.setAttribute("books", books);
		RequestDispatcher view = 
				request.getRequestDispatcher("/WEB-INF/JSPs/Answer.jsp");
		view.forward(request, response);
	}

}
