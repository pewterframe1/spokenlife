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


@WebServlet("/update_account")
public class update_account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
    public update_account() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String cf = request.getParameter("cf");
		String gender = request.getParameter("gender");
		String prof_image = "";
		String username = request.getParameter("username");
		
		User user = new User(username, name, surname, email, phone, password, cf, gender, prof_image);
		try {
			acc.UPDATE_ACCOUNT(user);
			response.sendRedirect(path + "/");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
