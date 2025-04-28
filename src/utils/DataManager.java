package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Movie;
import model.User;

/**
 * DataManager handles saving and loading of movie and user data.
 */
public class DataManager {
    private static final String MOVIE_FILE = "movies.ser";
    private static final String USER_FILE = "users.ser";

    /**
     * Saves the list of movies to a file.
     */
    public static void saveMovies(List<Movie> movieList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MOVIE_FILE))) {
            oos.writeObject(new ArrayList<>(movieList));
        } catch (IOException e) {
            System.err.println("Error saving movies: " + e.getMessage());
        }
    }

    /**
     * Loads the list of movies from a file.
     */
    @SuppressWarnings("unchecked")
    public static List<Movie> loadMovies() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVIE_FILE))) {
            return (List<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Movies file not found or invalid. Creating new empty list.");
            return new ArrayList<>();
        }
    }

    /**
     * Saves the list of users to a file.
     */
    public static void saveUsers(List<User> userList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(new ArrayList<>(userList));
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Loads the list of users from a file.
     */
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Users file not found or invalid. Creating new empty list.");
            return new ArrayList<>();
        }
    }
}
