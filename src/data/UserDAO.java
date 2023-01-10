package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Follower_Notification;
import model.Like;
import model.Post;
import model.User;
import model.Message;

public class UserDAO {
	private static final String INSERT_USER = "INSERT INTO user" + " (username, name, surname, email, phone, password, cf, gender, role, prof_image, online) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_POST = "INSERT INTO post" + " (date, text, likes, reported, hidden, topic, username) VALUES " + " (?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_LIKE = "INSERT INTO likes" + " (codice_post, username_like) VALUES" + " (?, ?)";
	private static final String INSERT_COMMENT = "INSERT INTO comments" + " (codpost, username_comment, text_comment) VALUES" + " (?, ?, ?)";
	private static final String INSERT_FOLLOW = "INSERT INTO following" + " (username1, username2) VALUES" + " (?, ?)";
	private static final String INSERT_FOLLOW_NOTIFICATION = "INSERT INTO following_notification" + " (username_mit, username_dest, seen) VALUES" + " (?, ?, ?)";
	private static final String INSERT_MESSAGE = "INSERT INTO messages" + " (date_mex, text_mess, username_mit_mess, username_dest_mess, seen_mess) VALUES" + " (?, ?, ?, ?,?)";
	private static final String UPDATE_MESSAGE = "UPDATE messages set seen_mess = 1 where idmex = ?";
	private static final String UPDATE_ACCOUNT = "UPDATE user set name=?, surname=?, email=?, phone=?, password=?, cf=?, gender=?, prof_image=? where username = ?;";
	private static final String UPDATE_FOLLOWING_NOTIFICATION = "UPDATE following_notification set seen=? where username_dest = ?;";
	private static final String UPDATE_ONLINE = "UPDATE user set online = ? where username = ?";
	private static final String DELETE_POST = "DELETE from post where id = ?;";
	private static final String DELETE_COMMENT = "DELETE from comments where id = ?;";
	private static final String DELETE_ACCOUNT = "DELETE from user where username = ?;";
	private static final String DELETE_LIKE = "DELETE from likes WHERE codice_post = ? AND username_like = ?;";
	private static final String DELETE_FOLLOW = "DELETE from following WHERE username1 = ? AND username2 = ?;";
	private static final String DELETE_FOLLOW_NOTIFICATION = "DELETE from following_notification where username_dest = ?;";
	private static final String DELETE_FOLLOW_NOTIFICATION_MIT = "DELETE from following_notification where username_mit = ? AND username_dest = ?;";
	private static final String SELECT_MESSAGES_BY_DEST = "SELECT * FROM messages WHERE username_dest_mess = ? ORDER BY idmex DESC";
	private static final String SELECT_ONLY_MIT = "SELECT idmex, username_mit_mess FROM messages WHERE username_dest_mess = ? GROUP BY username_mit_mess ORDER BY idmex DESC";
	private static final String SELECT_MESSAGES_BY_MIT_DEST_UNIVOC = "SELECT * FROM messages WHERE username_mit_mess = ? AND username_dest_mess = ? ORDER BY idmex";
	private static final String SELECT_MESSAGES_BY_MIT_DEST = "SELECT * FROM messages WHERE" + " (username_mit_mess = ? AND username_dest_mess = ?)" + " OR " + " (username_dest_mess = ? AND username_mit_mess = ?) ORDER BY idmex";
	private static final String SELECT_MESSAGES_NOTIFICATION = "SELECT * FROM messages WHERE seen_mess = 0 AND username_dest_mess = ?";
	private static final String SELECT_MESSAGES_NOTIFICATION_BY_MIT = "SELECT * FROM messages WHERE seen_mess = 0 AND username_dest_mess = ? AND username_mit_mess = ?";
	private static final String SELECT_LIKES = "SELECT * FROM likes where codice_post = ?"; 
	private static final String SELECT_ONLINE_USER = "SELECT * FROM user where online = 1";
	private static final String SELECT_NUM_FOLLOWERS = "SELECT * FROM following where username2 = ?";
	private static final String SELECT_NUM_NOTIFICHE = "SELECT * FROM following_notification where username_dest = ? and seen = 0";
	private static final String SELECT_FOLLOWERS_BY_USERNAME = "SELECT * FROM following_notification where username_dest = ?"; 
	private static final String SELECT_LIKES_BY_USERNAME = "SELECT * FROM likes where codice_post = ? AND username_like = ?";
	private static final String SELECT_FOLLOW_BY_USERNAME = "SELECT * FROM following where username1 = ? AND username2 = ?"; 
	private static final String SELECT_COMMENTS = "SELECT text_comment, username_comment FROM comments WHERE codpost = ?";
	private static final String SELECT_POST = "SELECT date, text, likes, topic, username FROM post WHERE idpost = ?";
	private static final String SELECT_ALL_POSTS = "SELECT idpost FROM post WHERE username=?";
	private static final String SELECT_POSTS_BY_USERNAME = "SELECT idpost, date, text, likes, reported, hidden, topic, username FROM post WHERE username = ? ORDER BY idpost DESC LIMIT 20";
	private static final String SELECT_LATEST_POSTS = "SELECT idpost, date, text, likes, reported, hidden, topic, username FROM post ORDER BY idpost DESC LIMIT 20";
	private static final String SELECT_ALL_FRIENDS = "SELECT username1, username2 FROM friendship WHERE username1 = ? OR username2 = ?;";
	private static final String SELECT_BY_USERNAME = "SELECT name, surname, email, phone, cf, gender, role, prof_image FROM user WHERE username =?";
	
	public UserDAO() {
		
	}
	
	public ArrayList<String> selectOnlyMit(String username_dest){
		ArrayList<String> listamit = new ArrayList<String>();
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONLY_MIT);
	    	preparedStatement.setString(1, username_dest);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String mit = rs.getString("username_mit_mess");
				listamit.add(mit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listamit;
	}
	
	public ArrayList<Message> selectMessagesByDest(String username_dest){
		ArrayList<Message> listamess = new ArrayList<Message>();
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_BY_DEST);
	    	preparedStatement.setString(1, username_dest);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Message mess = new Message();
				mess.setIdmex(rs.getInt("idmex"));
				mess.setMexdate(rs.getString("date_mex"));
				mess.setUsername_mit_mess(rs.getString("username_mit_mess"));
				mess.setUsername_dest_mess(rs.getString("username_dest_mess"));
				mess.setSeen_mess(rs.getInt("seen_mess"));
				listamess.add(mess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listamess;
	}
	
	public ArrayList<Message> selectMessagesByMitDest(String username_mit_mess, String username_dest_mess){
		ArrayList<Message> listamess = new ArrayList<Message>();
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_BY_MIT_DEST);
	    	preparedStatement.setString(1, username_mit_mess);
	    	preparedStatement.setString(2, username_dest_mess);
	    	preparedStatement.setString(3, username_mit_mess);
	    	preparedStatement.setString(4, username_dest_mess);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Message mess = new Message();
				mess.setIdmex(rs.getInt("idmex"));
				mess.setMexdate(rs.getString("date_mex"));
				mess.setUsername_mit_mess(rs.getString("username_mit_mess"));
				mess.setUsername_dest_mess(rs.getString("username_dest_mess"));
				mess.setText_mess(rs.getString("text_mess"));
				mess.setSeen_mess(rs.getInt("seen_mess"));
				listamess.add(mess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listamess;
	}
	
	public ArrayList<Message> selectMessagesByMitDestUnivoc(String username_mit_mess, String username_dest_mess){
		ArrayList<Message> listamess = new ArrayList<Message>();
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_BY_MIT_DEST_UNIVOC);
	    	preparedStatement.setString(1, username_mit_mess);
	    	preparedStatement.setString(2, username_dest_mess);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Message mess = new Message();
				mess.setIdmex(rs.getInt("idmex"));
				mess.setMexdate(rs.getString("date_mex"));
				mess.setUsername_mit_mess(rs.getString("username_mit_mess"));
				mess.setUsername_dest_mess(rs.getString("username_dest_mess"));
				mess.setText_mess(rs.getString("text_mess"));
				mess.setSeen_mess(rs.getInt("seen_mess"));
				listamess.add(mess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listamess;
	}
	
	public int selectAllMessagesNotification(String username_dest){
		int mess_not = 0;
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_NOTIFICATION);
	    	preparedStatement.setString(1, username_dest);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				mess_not++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mess_not;
	}
	
	public int selectMessagesNotificationByMit(String username_mit, String username_dest){
		int mess_not = 0;
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_NOTIFICATION_BY_MIT);
	    	preparedStatement.setString(1, username_dest);
	    	preparedStatement.setString(2, username_mit);
	    	ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				mess_not++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mess_not;
	}
	
	public void UPDATE_ONLINE(int online, String username) {
		try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ONLINE); 
            preparedStatement.setInt(1, online);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			try {
				ConnessioneDB.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void INSERT_USER(User user) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        	preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getCf());
			preparedStatement.setString(8, user.getGender());
			preparedStatement.setInt(9, user.getRole());
			preparedStatement.setString(10, user.getProf_image());
			preparedStatement.setInt(11, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void INSERT_POST(Post post) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST); 
            preparedStatement.setString(1, post.getPostdate());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setInt(3, post.getN_likes());
			preparedStatement.setInt(4, post.getReported());
			preparedStatement.setInt(5, post.getHidden());
			preparedStatement.setString(6, post.getTopic());
			preparedStatement.setString(7, post.getUsername());
			System.out.println(post);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void INSERT_LIKE(Like like) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIKE); 
            preparedStatement.setInt(1, like.getCodice_post());
            preparedStatement.setString(2, like.getUsername_like());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void INSERT_COMMENT(Comment comment) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT);
            preparedStatement.setInt(1, comment.getCodpost());
            preparedStatement.setString(2, comment.getUsername_comment());
            preparedStatement.setString(3, comment.getText_comment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void INSERT_FOLLOW(String username1, String username2) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOLLOW);
        	preparedStatement.setString(1, username1);
            preparedStatement.setString(2, username2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void INSERT_FOLLOW_NOTIFICATION(String username1, String username2, int seen_mess) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOLLOW_NOTIFICATION);
        	preparedStatement.setString(1, username1);
            preparedStatement.setString(2, username2);
            preparedStatement.setInt(3, seen_mess);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public ArrayList<Comment> SELECT_COMMENTS(int codpost) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			ConnessioneDB.connect();
	    	Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS);
	    	preparedStatement.setInt(1, codpost);
	    	ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setUsername_comment(rs.getString("username_comment"));
				comment.setText_comment(rs.getString("text_comment"));
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return comments;

	}
	
	public void INSERT_MESSAGE(Message mess) throws SQLException {
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE); 
            preparedStatement.setString(1, mess.getMexdate());
            preparedStatement.setString(2, mess.getText_mess());
			preparedStatement.setString(3, mess.getUsername_mit_mess());
			preparedStatement.setString(4, mess.getUsername_dest_mess());
			preparedStatement.setInt(5, mess.getSeen_mess());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
    }
	
	public void UPDATE_MESSAGE(Message mex) throws SQLException {
		 try {
	        	ConnessioneDB.connect();
	        	Connection connection = ConnessioneDB.getCon();
	        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE); 
	            preparedStatement.setInt(1, mex.getIdmex());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        } finally {
				ConnessioneDB.close();
			}
	}
	
	public boolean UPDATE_ACCOUNT(User user) throws SQLException {
        boolean rowUpdated = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);
        	statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(2, user.getEmail());
            statement.setString(2, user.getPhone());
            statement.setString(2, user.getPassword());
            statement.setString(2, user.getCf());
            statement.setString(2, user.getGender());
            statement.setString(3, user.getProf_image());
            statement.setString(6, user.getUsername());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowUpdated;
    }
	
	public boolean UPDATE_FOLLOWING_NOTIFICATION(Follower_Notification follower_notification) throws SQLException {
        boolean rowUpdated = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(UPDATE_FOLLOWING_NOTIFICATION);
        	String username_dest = follower_notification.getUsername_dest();
        	int seen = follower_notification.getSeen();
        	statement.setInt(1, seen);
        	statement.setString(2, username_dest);
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowUpdated;
    }
	
	public boolean DELETE_POST(int id) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_POST);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_COMMENT(int id) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_ACCOUNT(String username) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT);
            statement.setString(1, username);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_LIKE(int codice_post, String username_like) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_LIKE);
        	statement.setInt(1, codice_post);
            statement.setString(2, username_like);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_FOLLOW(String username1, String username2) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_FOLLOW);
        	statement.setString(1, username1);
            statement.setString(2, username2);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_FOLLOW_NOTIFICATION(String username_dest) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_FOLLOW_NOTIFICATION);
        	statement.setString(1, username_dest);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean DELETE_FOLLOW_NOTIFICATION_MIT(String username_mit, String username_dest) throws SQLException {
        boolean rowDeleted = true;
        try {
        	ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement statement = connection.prepareStatement(DELETE_FOLLOW_NOTIFICATION_MIT);
        	statement.setString(1, username_mit);
        	statement.setString(2, username_dest);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
        	e.printStackTrace();
        } finally {
			ConnessioneDB.close();
		}
        return rowDeleted;
    }
	
	public boolean checkLike(int codpost, String username) {
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_LIKES_BY_USERNAME);
			st.setInt(1, codpost);
			st.setString(2, username);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean checkFollow(String username1, String username2) {
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_FOLLOW_BY_USERNAME);
			st.setString(1, username1);
			System.out.println(username1);
			st.setString(2, username2);
			System.out.println(username2);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("Check effettuato");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public int selectLikes(int codpost) {
		int n_likes = 0;
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_LIKES);
			st.setInt(1, codpost);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				n_likes++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n_likes;
	}
	
	public int selectFollowers(String username2) {
		int n_followers = 0;
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_NUM_FOLLOWERS);
			st.setString(1, username2);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				n_followers++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n_followers;
	}
	
	public int selectNumNotifiche(String username2) {
		int n_notifiche = 0;
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_NUM_NOTIFICHE);
			st.setString(1, username2);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				n_notifiche++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n_notifiche;
	}
	
	public ArrayList<User> selectOnlineUser(){
		ArrayList<User> onlineuser = new  ArrayList<User>();
		try {
			ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement ps = connection.prepareStatement(SELECT_ONLINE_USER);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setCf(rs.getString("cf"));
				user.setOnline(rs.getInt("online"));
				user.setProf_image(rs.getString("prof_image"));
				if(user.getOnline() == 1) {
					onlineuser.add(user);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return onlineuser;
	}

	public User selectByUsername(String username) throws SQLException{
		User user = new User();
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
			PreparedStatement st = connection.prepareStatement(SELECT_BY_USERNAME);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			while (rs.next()) 
			{
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String cf = rs.getString("cf");
				String gender = rs.getString("gender");
				int role = rs.getInt("role");
				String prof_image = rs.getString("prof_image");
				user = new User(username, name, surname, email, phone, cf, gender, role, prof_image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnessioneDB.close();
		}
		return user;
	}
	
	public ArrayList<Integer> selectAllPost(String username){
		ArrayList<Integer> posts = new ArrayList<Integer>();
		try {
			ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement ps = connection.prepareStatement(SELECT_ALL_POSTS);
			ps.setString(1, username);
        	
			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				int idpost = rs.getInt("codice_post");
				posts.add(idpost);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public Post selectPostById(int id) {
		Post p = new Post();
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement ps = connection.prepareStatement(SELECT_POST);
	    	ps.setInt(1, id);
	    	ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				p.setIdpost(rs.getInt("idpost"));
				p.setPostdate(rs.getString("date"));
				p.setText(rs.getString("text"));
				p.setN_likes(rs.getInt("likes"));
				p.setReported(rs.getInt("reported"));
				p.setHidden(rs.getInt("hidden"));
				p.setTopic(rs.getString("topic"));
				p.setUsername(rs.getString("username"));
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return p;
	}
	
	public ArrayList<Post> selectPostByUsername(String username){
		ArrayList<Post> listapost = new ArrayList<Post>();
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement ps = connection.prepareStatement(SELECT_POSTS_BY_USERNAME);
	    	ps.setString(1, username);
	    	ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Post p = new Post();
				p.setIdpost(rs.getInt("idpost"));
				p.setPostdate(rs.getString("date"));
				p.setText(rs.getString("text"));
				p.setN_likes(rs.getInt("likes"));
				p.setReported(rs.getInt("reported"));
				p.setHidden(rs.getInt("hidden"));
				p.setTopic(rs.getString("topic"));
				p.setUsername(rs.getString("username"));
				listapost.add(p);
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listapost;
	} 
	
	public ArrayList<Follower_Notification> selectNotificationByUsername(String username){
		ArrayList<Follower_Notification> listanotifichefollowing = new ArrayList<Follower_Notification>();
		UserDAO acc = new UserDAO();
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement pus = connection.prepareStatement(SELECT_FOLLOWERS_BY_USERNAME);
	    	pus.setString(1, username);
	    	ResultSet rs = pus.executeQuery();

			while (rs.next()) {
				Follower_Notification fn = new Follower_Notification();
				fn.setIdfollowing_notificaton(rs.getInt("idfollowing_notification"));
				fn.setUsername_mit(rs.getString("username_mit"));
				fn.setUsername_dest(rs.getString("username_dest"));
				fn.setSeen(1);
				listanotifichefollowing.add(fn);
			}
			
			for(int k = 0; k<listanotifichefollowing.size(); k++){
				acc.UPDATE_FOLLOWING_NOTIFICATION(listanotifichefollowing.get(k));
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listanotifichefollowing;
	}
	
	public ArrayList<Post> selectLatestPost() {
		ArrayList<Post> lista_p = new ArrayList<Post>();
		try {
			ConnessioneDB.connect();
			Connection connection = ConnessioneDB.getCon();
	    	PreparedStatement ps = connection.prepareStatement(SELECT_LATEST_POSTS);

	    	ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Post p = new Post();
				p.setIdpost(rs.getInt("idpost"));
				p.setPostdate(rs.getString("date"));
				p.setText(rs.getString("text"));
				p.setN_likes(rs.getInt("likes"));
				p.setReported(rs.getInt("reported"));
				p.setHidden(rs.getInt("hidden"));
				p.setTopic(rs.getString("topic"));
				p.setUsername(rs.getString("username"));
				lista_p.add(p);
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista_p;
	}
	
	
	public ArrayList<User> selectAmiciByUsername(String username){
		ArrayList<User> friends = new ArrayList<User>();
		try {
			ConnessioneDB.connect();
        	Connection connection = ConnessioneDB.getCon();
        	PreparedStatement ps = connection.prepareStatement(SELECT_ALL_FRIENDS);
			ps.setString(1, username);
			ps.setString(2, username);
        	
			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				User friend = null;
				if(rs.getString("username1") == username){
					friend = selectByUsername(rs.getString("username2"));
				}else if(rs.getString("username2") == username){
					friend = selectByUsername(rs.getString("username1"));
				}
				
				if(friend != null) {
					friends.add(friend);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return friends;
	}
	
	public User userLogin(String username, String password) throws SQLException{
		User user = new User();
		UserDAO acc = new UserDAO();
        try {
        	ConnessioneDB.connect();
        	Connection connect = ConnessioneDB.getCon();
            String query = "select * from user where username=? and password=?";
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            	user = acc.selectByUsername(username);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
        	ConnessioneDB.close();
        }
        return user;
    }

}
