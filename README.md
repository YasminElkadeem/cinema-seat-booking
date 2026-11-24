# 🎬 Cinema Seat Booking System

A simple Java application that simulates a cinema seat booking system using **HashMap** and **HashSet**.

This project was created as a practical Java lab task to understand how HashMap works with custom objects as keys and why `equals()` and `hashCode()` are important.

## 📌 Project Overview

The cinema hall contains:
- 5 rows (A to E).
- 6 seats in each row.
- A total of 30 seats.

The application manages customer bookings, checks seat availability, prevents booking reserved or broken seats, and displays the cinema seating chart.

## 🛠️ Technologies Used

- Java
- Java Collections Framework
- HashMap
- HashSet
- Java Records

## ✨ Features

- **Book Seats:** Store seat bookings using `HashMap`.
- **Find Customers:** Retrieve the customer name using `get()`.
- **Check Availability:** Check whether a seat is already booked using `containsKey()`.
- **Prevent Duplicate Bookings:** Use `putIfAbsent()` to avoid replacing existing bookings.
- **Move Bookings:** Remove an old booking and assign a new seat.
- **Manage Broken Seats:** Store broken seats using `HashSet`.
- **Check Broken Seats:** Prevent customers from booking broken seats using `contains()`.
- **Count Bookings:** Get the total number of booked seats using `size()`.
- **Display Seating Chart:** Show booked, broken, and available seats.

## 🧠 Java Concepts Practiced

| Concept | Purpose |
|---|---|
| HashMap | Store seats as keys and customer names as values |
| HashSet | Store broken seats without duplicates |
| put() | Add new bookings |
| get() | Retrieve customer information |
| containsKey() | Check if a seat is booked |
| putIfAbsent() | Prevent replacing existing bookings |
| remove() | Remove an existing booking |
| add() | Add broken seats |
| contains() | Check if a seat is broken |
| size() | Count current bookings |
| equals() | Compare Seat objects based on their data |
| hashCode() | Generate consistent hash codes for equal objects |
| Record | Create an immutable Seat object with automatically generated methods |

## 💡 Important Learning Point

When using custom objects as keys in a HashMap or elements in a HashSet, overriding `equals()` and `hashCode()` is essential.

Two Seat objects are considered equal when they have the same row and seat number, even if they are different objects in memory.

**Bonus:** Java Records automatically generate `equals()`, `hashCode()`, and `toString()`, making them a convenient choice for immutable data objects.

## ▶️ How to Run

1. Clone the repository:

```bash
git clone YOUR_REPOSITORY_URL
```

2. Open the project folder.

3. Compile the Java file:

```bash
javac cinemaBooking.java
```

4. Run the application:

```bash
java cinemaBooking
```

**Requirements:** Java 16 or later (for Records).

## 📊 Expected Output

```text
Bookings:           {A1=Ali, C3=Mona, A2=Sara, B5=Omar}
Who booked C3:      Mona
Is A2 taken:        true
Is E6 taken:        false
Youssef -> B5:      refused, taken by Omar
After Ali moved:    {C3=Mona, A2=Sara, B5=Omar, D2=Ali}
Hana -> E1:         refused, seat is broken
Seats booked