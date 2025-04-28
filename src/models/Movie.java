package models;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String showtime;
    private int availableSeats;
    private double price;

    public Movie(String title, String showtime, int availableSeats, double price) {
        this.title = title;
        this.showtime = showtime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getShowtime() {
        return showtime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
