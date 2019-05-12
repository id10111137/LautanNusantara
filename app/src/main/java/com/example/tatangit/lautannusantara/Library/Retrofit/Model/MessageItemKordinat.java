package com.example.tatangit.lautannusantara.Library.Retrofit.Model;

public class MessageItemKordinat {
	private String keterangan;
	private String latitude;
	private String longtitude;
	private String noKordinat;

	public MessageItemKordinat(String noKordinat, String latitude, String longtitude, String keterangan) {
		this.keterangan = keterangan;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.noKordinat = noKordinat;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setLongtitude(String longtitude){
		this.longtitude = longtitude;
	}

	public String getLongtitude(){
		return longtitude;
	}

	public void setNoKordinat(String noKordinat){
		this.noKordinat = noKordinat;
	}

	public String getNoKordinat(){
		return noKordinat;
	}

	@Override
 	public String toString(){
		return 
			"MessageItemKordinat{" +
			"keterangan = '" + keterangan + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			",noKordinat = '" + noKordinat + '\'' + 
			"}";
		}
}
