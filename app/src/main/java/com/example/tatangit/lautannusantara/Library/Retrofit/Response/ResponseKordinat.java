package com.example.tatangit.lautannusantara.Library.Retrofit.Response;

import java.util.List;

import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemKordinat;
import com.google.gson.annotations.SerializedName;

public class ResponseKordinat{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private List<MessageItemKordinat> message;

	@SerializedName("status")
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