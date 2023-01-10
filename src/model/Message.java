package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private int idmex;
	private String mexdate;
	private String text_mess;
	String username_mit_mess;
	String username_dest_mess;
	int seen_mess;
	
	public Message() {
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.mexdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text_mess = "";
		this.username_mit_mess = "";
		this.username_dest_mess = "";
		this.seen_mess=0;
	}

	public Message(int idmex, String mexdate, String text_mess, String username_mit_mess, String username_dest_mess, int seen_mess) {
		super();
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.idmex = idmex;
		this.mexdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text_mess = text_mess;
		this.username_mit_mess = username_mit_mess;
		this.username_dest_mess = username_dest_mess;
		this.seen_mess = seen_mess;
	}

	public Message(String username_mit_mess, String username_dest_mess, int seen_mess) {
		super();
		this.username_mit_mess = username_mit_mess;
		this.username_dest_mess = username_dest_mess;
		this.seen_mess = seen_mess;
	}

	public Message(String mexdate, String text_mess, String username_mit_mess, String username_dest_mess, int seen_mess) {
		super();
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.mexdate = currentLocalDateTime.format(dateTimeFormatter);
		this.text_mess = text_mess;
		this.username_mit_mess = username_mit_mess;
		this.username_dest_mess = username_dest_mess;
		this.seen_mess = seen_mess;
	}

	public Message(int idmex, int seen_mess) {
		super();
		this.idmex = idmex;
		this.seen_mess = seen_mess;
	}

	public Message(int idmex, String username_mit_mess, String username_dest_mess, int seen_mess) {
		super();
		this.idmex = idmex;
		this.username_mit_mess = username_mit_mess;
		this.username_dest_mess = username_dest_mess;
		this.seen_mess = seen_mess;
	}

	public int getIdmex() {
		return idmex;
	}
	public void setIdmex(int idmex) {
		this.idmex = idmex;
	}
	public String getMexdate() {
		return mexdate;
	}
	public void setMexdate(String mexdate) {
		this.mexdate = mexdate;
	}

	public String getText_mess() {
		return text_mess;
	}

	public void setText_mess(String text_mess) {
		this.text_mess = text_mess;
	}

	public String getUsername_mit_mess() {
		return username_mit_mess;
	}

	public void setUsername_mit_mess(String username_mit_mess) {
		this.username_mit_mess = username_mit_mess;
	}

	public String getUsername_dest_mess() {
		return username_dest_mess;
	}

	public void setUsername_dest_mess(String username_dest_mess) {
		this.username_dest_mess = username_dest_mess;
	}

	public int getSeen_mess() {
		return seen_mess;
	}

	public void setSeen_mess(int seen_mess) {
		this.seen_mess = seen_mess;
	}

	@Override
	public String toString() {
		return "Message [idmex=" + idmex + ", mexdate=" + mexdate + ", text_mess=" + text_mess + ", username_mit_mess="
				+ username_mit_mess + ", username_dest_mess=" + username_dest_mess + ", seen_mess=" + seen_mess + "]";
	}

}
