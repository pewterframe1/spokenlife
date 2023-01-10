package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import data.UserDAO;

public class Post {
	private int idpost;
	private String postdate;
	private String text;
	private String topic;
	private int reported;
	private int hidden;
	private int n_likes;
	private String username;
	
//COSTRUTTORI:
	public Post() {
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.postdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text = "";
		this.topic = "";
		this.reported = 0;
		this.hidden = 0;
		this.n_likes = 0;
		this.username = "";
	}
	
	public Post(String text, String topic, int reported, int hidden,
			int n_likes, String username) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.postdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text = text;
		this.topic = topic;
		this.reported = reported;
		this.hidden = hidden;
		this.n_likes = n_likes;
		this.username = username;
	}
	
	public Post(int idpost, String text, String topic, int reported, int hidden,
			 int n_likes, String username) {
		super();
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.idpost = idpost;
		this.postdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text = text;
		this.topic = topic;
		this.reported = reported;
		this.hidden = hidden;
		this.n_likes = n_likes;
		this.username = username;
	}

//GET E SET:
	public int getIdpost() {
		return idpost;
	}
	
	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getReported() {
		return reported;
	}

	public void setReported(int reported) {
		this.reported = reported;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public int getN_likes() {
		return n_likes;
	}

	public void setN_likes(int n_likes) {
		this.n_likes = n_likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Post [idpost=" + idpost + ", postdate=" + postdate + ", text=" + text + ", topic=" + topic
				+ ", reported=" + reported + ", hidden=" + hidden + ", n_likes=" + n_likes + ", username=" + username
				+ "]";
	}
	
	
}