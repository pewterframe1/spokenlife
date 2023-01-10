package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDAO;
import model.User;

/**
 * Servlet implementation class changepassword
 */
@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username,password);
		try {
			acc.UPDATE_ACCOUNT(user);
			response.sendRedirect(path + "/");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
