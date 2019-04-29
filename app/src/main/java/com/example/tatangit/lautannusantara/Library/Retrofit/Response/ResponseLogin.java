package com.example.tatangit.lautannusantara.Library.Retrofit.Response;

import java.util.List;

import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private List<MessageItemLogin> message;

	@SerializedName("status")
	private String status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(List<MessageItemLogin> message){
		this.message = message;
	}

	public List<MessageItemLogin> getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}