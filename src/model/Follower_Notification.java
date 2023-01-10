package model;

public class Follower_Notification {
	private int idfollowing_notificaton;
	private String username_mit;
	private String username_dest;
	private int seen;
	
	public Follower_Notification() {
		
	}
	
	public Follower_Notification(int idfollowing_notificaton, String username_mit, String username_dest, int seen) {
		super();
		this.idfollowing_notificaton = idfollowing_notificaton;
		this.username_mit = username_mit;
		this.username_dest = username_dest;
		this.seen = seen;
	}
	
	public Follower_Notification(String username_mit, String username_dest, int seen) {
		super();
		this.username_mit = username_mit;
		this.username_dest = username_dest;
		this.seen = seen;
	}
	
	public int getIdfollowing_notificaton() {
		return idfollowing_notificaton;
	}
	public void setIdfollowing_notificaton(int idfollowing_notificaton) {
		this.idfollowing_notificaton = idfollowing_notificaton;
	}
	public String getUsername_mit() {
		return username_mit;
	}
	public void setUsername_mit(String username_mit) {
		this.username_mit = username_mit;
	}
	public String getUsername_dest() {
		return username_dest;
	}
	public void setUsername_dest(String username_dest) {
		this.username_dest = username_dest;
	}
	public int getSeen() {
		return seen;
	}
	public void setSeen(int seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "Follower_Notification [idfollowing_notificaton=" + idfollowing_notificaton + ", username_mit="
				+ username_mit + ", username_dest=" + username_dest + ", seen=" + seen + "]";
	}
	
	
	
}