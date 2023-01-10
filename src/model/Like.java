package model;

public class Like {
	private int codice_post;
	private String username_like;
	
	public Like(int codice_post, String username_like) {
		super();
		this.codice_post = codice_post;
		this.username_like = username_like;
	}

	public int getCodice_post() {
		return codice_post;
	}

	public void setCodice_post(int codice_post) {
		this.codice_post = codice_post;
	}

	public String getUsername_like() {
		return username_like;
	}

	public void setUsername_like(String username_like) {
		this.username_like = username_like;
	}
	
	
}
