package model;

import java.io.Serializable;

public class Booking {
    private Movie movie;
    private int seats;
    private boolean paid;

    public Booking(Movie movie, int seats) {
        this.movie = movie;
        this.seats = seats;
        this.paid = true;
    }

    public Movie getMovie() { return movie; }
    public int getSeats() { return seats; }
    public boolean isPaid() { return paid; }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " | Seats: " + seats;
    }
}
