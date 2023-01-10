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
import model.Post;
import model.User;

@WebServlet("/insert_post")
public class insert_post extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
       
    public insert_post() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		User user = (User)request.getSession().getAttribute("auth");
		String username = user.getUsername();
		String text = request.getParameter("texts");
		String topic = "defaul topic";
		try {
			Post post = new Post(text, topic, 0, 0, 0, username);
			acc.INSERT_POST(post);
			response.sendRedirect(path + "/home.jsp");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}