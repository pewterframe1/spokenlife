package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDAO;
import model.Message;
import model.User;


@WebServlet("/insert_message")
public class insert_message extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO acc;
 
    public insert_message() {
        super();
       
    }

	
	public void init(ServletConfig config) throws ServletException {
		acc = new UserDAO();
	}

	
	public void destroy() {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		User user = (User)request.getSession().getAttribute("auth");
		String text = request.getParameter("text_mess"); 
		String mexdate = LocalDateTime.now().toString(); 
		String username1 = user.getUsername();
		String username2 = request.getParameter("dest");
		int seen = 0;
		Message message = new Message(mexdate,text,username1,username2,seen);
		try {
			acc.INSERT_MESSAGE(message);
			ArrayList<Message> listamessmit = new ArrayList<Message>();
			listamessmit = acc.selectMessagesByMitDestUnivoc(username2, username1);
			for (int i = 0; i < listamessmit.size(); i++) {
				acc.UPDATE_MESSAGE(listamessmit.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(path + "/chat.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
