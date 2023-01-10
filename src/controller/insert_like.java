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
import model.Like;
import model.User;

@WebServlet("/insert_like")
public class insert_like extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
       
    public insert_like() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		int codpost = Integer.parseInt(request.getParameter("likebutton"));
		User user = (User)request.getSession().getAttribute("auth");
		String username = user.getUsername();
		if(acc.checkLike(codpost,username)) {
			try {
				acc.DELETE_LIKE(codpost, username);
				response.sendRedirect(path + "/home.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			Like like = new Like(codpost, username);
			try {
				acc.INSERT_LIKE(like);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(path + "/home.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}