package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

public class MessageItemLogin {
	private String password;
	private String id_user;
	private String role;
	private String username;

	public MessageItemLogin(String id_user, String username, String password, String role) {
		this.id_user = id_user;
		this.password = password;
		this.role = role;
		this.username = username;
	}


	public void setId_user(String id_user){
		this.id_user = id_user;
	}

	public String getId_user(){
		return id_user;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
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
					"id_user = '" + id_user + '\'' +

					",password = '" + password + '\'' +
			",role = '" + role + '\'' +
			",username = '" + username + '\'' + 
			"}";
		}
}
