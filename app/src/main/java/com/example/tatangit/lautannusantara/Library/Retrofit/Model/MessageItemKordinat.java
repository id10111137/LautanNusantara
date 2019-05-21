package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class MessageItemKordinat {

	@SerializedName("jumlah_klorofil")
	private String jumlahKlorofil;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("id_klorofil")
	private String idKlorofil;

	@SerializedName("date_update")
	private String dateUpdate;

	@SerializedName("id_koordinat")
	private String idKoordinat;

	@SerializedName("longitude")
	private String longitude;

	public void setJumlahKlorofil(String jumlahKlorofil){
		this.jumlahKlorofil = jumlahKlorofil;
	}

	public String getJumlahKlorofil(){
		return jumlahKlorofil;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setIdKlorofil(String idKlorofil){
		this.idKlorofil = idKlorofil;
	}

	public String getIdKlorofil(){
		return idKlorofil;
	}

	public void setDateUpdate(String dateUpdate){
		this.dateUpdate = dateUpdate;
	}

	public String getDateUpdate(){
		return dateUpdate;
	}

	public void setIdKoordinat(String idKoordinat){
		this.idKoordinat = idKoordinat;
	}

	public String getIdKoordinat(){
		return idKoordinat;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"MessageItemKordinat{" +
			"jumlah_klorofil = '" + jumlahKlorofil + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",id_klorofil = '" + idKlorofil + '\'' + 
			",date_update = '" + dateUpdate + '\'' + 
			",id_koordinat = '" + idKoordinat + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}