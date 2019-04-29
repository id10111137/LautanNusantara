package com.example.tatangit.lautannusantara.Home.Model;

public class InfoWindowData {

    private String image;
    private String hotel;
    private String food;
    private String transport;

    public InfoWindowData(String image, String hotel, String food, String transport) {
        this.image = image;
        this.hotel = hotel;
        this.food = food;
        this.transport = transport;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

}
