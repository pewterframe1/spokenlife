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

@WebServlet("/registrazione")
public class registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
       
    public registrazione() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String cf = request.getParameter("cf");
		String gender = request.getParameter("gender");
		int role = 0;
		String prof_image = "https://1.bp.blogspot.com/-oBDvKkrQiVs/YNCvGDNX0YI/AAAAAAAAorc/Lx148JTmljEAErgD4zVJD0U0mkU6G6iXgCLcBGAsYHQ/w320-h198/Account-solo-Google.jpg";
		try {
			if(acc.selectByUsername(username).getUsername() == null) {
				if(password.equals(confirm_password)) {
					System.out.println("Entro nella insert");
					User user = new User(username, name, surname, email, phone, password, cf, gender, role, prof_image);
					acc.INSERT_USER(user);
					response.sendRedirect(path + "/login.jsp?username=" + username + "&singup=true");
				}else {
					response.sendRedirect(path + "/registrazione.jsp?singup=false");
				}
				
			}else {
				response.sendRedirect(path + "/login.jsp");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
