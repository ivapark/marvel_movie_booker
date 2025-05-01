# Marvel Movie Ticket Booking System

## Project Overview
The Marvel Movie Ticket Booking System is a GUI-based Java application that allows customers to browse Marvel movies, book and cancel tickets, and manage their user profiles.  
Admins can add, modify, delete movies, view registered users, and track revenue per movie.

The system uses Java for the GUI, and object oriented programming to save and load movie, booking, and user data across sessions.

---

## Features

### Customers
- Browse released Marvel movies (by title or showtime)
- Book movie tickets (choose movie, seats, and see amount owed to pay)
- View, modify, or cancel existing bookings
- Create or update user profile

### Admins
- Add new Marvel movies with title, showtime, seats, and price
- Modify existing movie details
- Remove outdated movies
- View all users registered for movies
- Track and view revenue per movie

---

## How to Compile and Run

### Step 1: Compile

```bash
javac -d ../out $(find . -name "*.java")
```
- This compiles all Java source files from `src/` into `out/`

---

### Step 2: Run

```bash
java -cp ../out Main    
```
- This runs the application starting from the `Main.java` class

---

## Team Members
- **Iva Park** (<cp3588@nyu.edu>)
- **Chrisim Kim** (<ck3388@nyu.edu>)

---

## Notes
- Admin features require password authentication (PW: marvel123)
- Input validation (e.g., no negative seats) should be added for production version.
- Project designed as part of NYU Object-Oriented Programming course.

---