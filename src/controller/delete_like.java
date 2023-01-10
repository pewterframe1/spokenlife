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


@WebServlet("/delete_like")
public class delete_like extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
	
    public delete_like() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		int codice_post = Integer.parseInt(request.getParameter("codice_post"));
		String username = request.getParameter("username_like");
		try {
			acc.DELETE_LIKE(codice_post, username);
			response.sendRedirect(path + "/");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
