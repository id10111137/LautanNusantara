package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class MessageItemLogin {

	public MessageItemLogin(String nomorMember, String namaMember, String usernameMember, String passwordMember,  String emailMember,  String statusMember) {
		this.emailMember = emailMember;
		this.nomorMember = nomorMember;
		this.namaMember = namaMember;
		this.passwordMember = passwordMember;
		this.usernameMember = usernameMember;
		this.statusMember = statusMember;
	}

	@SerializedName("email_member")
	private String emailMember;

	@SerializedName("nomor_member")
	private String nomorMember;

	@SerializedName("nama_member")
	private String namaMember;

	@SerializedName("password_member")
	private String passwordMember;

	@SerializedName("username_member")
	private String usernameMember;

	@SerializedName("status_member")
	private String statusMember;

	public void setEmailMember(String emailMember){
		this.emailMember = emailMember;
	}

	public String getEmailMember(){
		return emailMember;
	}

	public void setNomorMember(String nomorMember){
		this.nomorMember = nomorMember;
	}

	public String getNomorMember(){
		return nomorMember;
	}

	public void setNamaMember(String namaMember){
		this.namaMember = namaMember;
	}

	public String getNamaMember(){
		return namaMember;
	}

	public void setPasswordMember(String passwordMember){
		this.passwordMember = passwordMember;
	}

	public String getPasswordMember(){
		return passwordMember;
	}

	public void setUsernameMember(String usernameMember){
		this.usernameMember = usernameMember;
	}

	public String getUsernameMember(){
		return usernameMember;
	}

	public void setStatusMember(String statusMember){
		this.statusMember = statusMember;
	}

	public String getStatusMember(){
		return statusMember;
	}

	@Override
 	public String toString(){
		return 
			"MessageItemLogin{" +
			"email_member = '" + emailMember + '\'' + 
			",nomor_member = '" + nomorMember + '\'' + 
			",nama_member = '" + namaMember + '\'' + 
			",password_member = '" + passwordMember + '\'' + 
			",username_member = '" + usernameMember + '\'' + 
			",status_member = '" + statusMember + '\'' + 
			"}";
		}
}