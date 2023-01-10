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

@WebServlet("/follow")
public class follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
       
    public follow() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		User user = (User)request.getSession().getAttribute("auth");
		String timelineuser = request.getParameter("username");
		String username = user.getUsername();
		if(acc.checkFollow(username, timelineuser)) {
			try {
				acc.DELETE_FOLLOW(username, timelineuser);
				acc.DELETE_FOLLOW_NOTIFICATION_MIT(username, timelineuser);
				response.sendRedirect(path + "/timeline.jsp?username=" + timelineuser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			try {
				acc.INSERT_FOLLOW(username, timelineuser);
				acc.INSERT_FOLLOW_NOTIFICATION(username, timelineuser, 0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(path + "/timeline.jsp?username=" + timelineuser);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}