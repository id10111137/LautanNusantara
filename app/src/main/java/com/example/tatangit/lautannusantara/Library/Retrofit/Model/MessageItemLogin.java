package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

public class MessageItemLogin {
	private String password;

	public MessageItemLogin(String noUser, String username, String password, String email) {
		this.password = password;
		this.noUser = noUser;
		this.email = email;
		this.username = username;
	}

	private String noUser;
	private String email;
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNoUser(String noUser){
		this.noUser = noUser;
	}

	public String getNoUser(){
		return noUser;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"MessageItemLogin{" +
			"password = '" + password + '\'' + 
			",noUser = '" + noUser + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
