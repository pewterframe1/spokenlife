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
import model.Message;
import model.User;

/**
 * Servlet implementation class updatemessage
 */
@WebServlet("/updatemessage")
public class updatemessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatemessage() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		User user = (User)request.getSession().getAttribute("auth");
		String username1 = user.getUsername();
		String username2 = request.getParameter("username_dest_mess");
		int seen = 1;
		Message message = new Message(username1,username2,seen);
		try {
			acc.UPDATE_MESSAGE(message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(path + "newsfeed-messages.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
