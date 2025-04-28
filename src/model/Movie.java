package model;

import java.io.Serializable;

public class Movie {
    private String title;
    private String showtime;
    private int seatsAvailable;

    public Movie(String title, String showtime, int seatsAvailable) {
        this.title = title;
        this.showtime = showtime;
        this.seatsAvailable = seatsAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getShowtime() {
        return showtime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }    

    public boolean bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
            return true;
        }
        return false;
    }

    public void cancelSeat() {
        seatsAvailable++;
    }

    @Override
    public String toString() {
        return title + " | Showtime: " + showtime + " | Available Seats: " + seatsAvailable;
    }
}
