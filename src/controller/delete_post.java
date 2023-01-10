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


@WebServlet("/delete_post")
public class delete_post extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
	
    public delete_post() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		int codice_post = Integer.parseInt(request.getParameter("id"));
		try {
			acc.DELETE_POST(codice_post);
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
