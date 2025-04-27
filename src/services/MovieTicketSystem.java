package services;

import java.util.ArrayList;
import java.util.List;
import models.Movie;
import models.User;
import models.Booking;

public class MovieTicketSystem {
    private List<Movie> movies;
    private List<User> users;
    private List<Booking> bookings;

    public MovieTicketSystem() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}
