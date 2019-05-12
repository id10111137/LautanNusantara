package com.example.tatangit.lautannusantara.Library.Retrofit.Response;

import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemKordinat;

import java.util.List;

public class ResponseKordinat{
	private int code;
	private List<MessageItemKordinat> message;
	private String status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(List<MessageItemKordinat> message){
		this.message = message;
	}

	public List<MessageItemKordinat> getMessage(){
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
			"ResponseKordinat{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}