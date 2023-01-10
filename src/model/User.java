package model;

import java.util.List;

public class User {
	private String username;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String password;
	private String cf;
	private String gender;
	private int role;
	private String prof_image;
	private int online;
	
	public User() {
		
	}
	
	public User(String username, String name, String surname, String email, String phone, String password, String cf, String gender,
			int role, String prof_image) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.cf = cf;
		this.gender = gender;
		this.role = role;
		this.prof_image = prof_image;
		this.online = 0;
	}

	public User(String username, String name, String surname, String email, String phone, String password, String cf, String gender,
			int role, int n_friends, String prof_image) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.cf = cf;
		this.gender = gender;
		this.role = role;
		this.prof_image = prof_image;
	}
	
	public User(String username, String name, String surname, String email, String phone, String cf, String gender, int role, String  prof_image) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.cf = cf;
		this.gender = gender;
		this.role = role;
		this.prof_image = prof_image;
	}
	
	public User(String username,String name,String surname,String email,String phone,String password,String cf,String gender,String prof_image) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.cf = cf;
		this.gender = gender;
		this.prof_image = prof_image;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


	public String getProf_image() {
		return prof_image;
	}

	public void setProf_image(String prof_images) {
		this.prof_image = prof_images;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", cf=" + cf + ", gender=" + gender + ", role=" + role
				+ ", prof_image=" + prof_image + ", online=" + online + "]";
	}


	
	
	
	
}
