package com.myfirstservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* Servlet implementation class LoginServlet
*/

@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Sippora"), @WebInitParam(name = "password", value = "admin123") })

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");

		if (!user.matches("^[A-Z][A-Za-z]{2,}$")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>User name must start with captial letter and contain min 3 characters</font>");
			rd.include(request, response);
		} else if (!pwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Password is not correct. Try again!</font>");
			rd.include(request, response);
		} else if (userID.equals(user) && password.equals(pwd)) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong</font>");
			rd.include(request, response);
		}
	}
}