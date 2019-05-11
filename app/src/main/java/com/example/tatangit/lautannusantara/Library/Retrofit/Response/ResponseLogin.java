package com.example.tatangit.lautannusantara.Library.Retrofit.Response;

import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;

import java.util.List;

public class ResponseLogin{
	private int code;
	private List<MessageItemLogin> message;
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