package model;

public class Comment {
	private int idcomment;
	private int codpost;
	private String username_comment;
	private String text_comment;
	
	public Comment() {}
	
	public Comment(int codpost, String username_comment, String text_comment) {
		super();
		this.codpost = codpost;
		this.username_comment = username_comment;
		this.text_comment = text_comment;
	}

	public int getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public int getCodpost() {
		return codpost;
	}

	public void setCodpost(int codpost) {
		this.codpost = codpost;
	}

	public String getUsername_comment() {
		return username_comment;
	}

	public void setUsername_comment(String username_comment) {
		this.username_comment = username_comment;
	}

	public String getText_comment() {
		return text_comment;
	}

	public void setText_comment(String text_comment) {
		this.text_comment = text_comment;
	}
	
	
}
