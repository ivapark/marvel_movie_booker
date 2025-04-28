import java.util.List;
import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private List<Booking> bookings;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Booking> getBookings() { return bookings; }

    public void addBooking(Booking booking) { bookings.add(booking); }
    public void removeBooking(Booking booking) { bookings.remove(booking); }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
