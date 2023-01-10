package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserDAO;
import model.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	UserDAO userdao = new UserDAO();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			UserDAO acc = new UserDAO();
			try {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				//ConnessioneDb.connect();
				//UserDao udao = new UserDao(ConnessioneDb.getCon());
				User user = acc.userLogin(username, password);
				if (user != null) {
					request.getSession().setMaxInactiveInterval(1800); 
					request.getSession().setAttribute("auth", user);
					request.setAttribute("user", user);
					acc.UPDATE_ONLINE(1, username);
					response.sendRedirect("home.jsp");
				} else {
					response.sendRedirect("login.jsp");
				}

			} catch (/*ClassNotFoundException|*/SQLException e) {
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
	
	public void destroy(){
		
	}

}
