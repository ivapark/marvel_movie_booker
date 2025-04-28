package models;

import java.io.Serializable;

public class Booking implements Serializable {
    private Movie movie;
    private User user;
    private int numberOfSeats;
    private double amountPaid;

    public Booking(Movie movie, User user, int numberOfSeats, double amountPaid) {
        this.movie = movie;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
        this.amountPaid = amountPaid;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
