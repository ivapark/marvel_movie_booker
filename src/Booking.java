public class Booking {
    private Movie movie;
    private int seats;
    private boolean paid;

    public Booking(Movie movie, int seats) {
        this.movie = movie;
        this.seats = seats;
        this.paid = true; // assume always paid for now
    }

    public Movie getMovie() { return movie; }
    public int getSeats() { return seats; }
    public boolean isPaid() { return paid; }

    @Override
    public String toString() {
        return movie.getTitle() + " | Seats: " + seats;
    }
}
