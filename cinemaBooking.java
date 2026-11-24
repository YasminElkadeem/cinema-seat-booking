import java.util.*;

class cinemaBooking {

    // A seat in the cinema hall, for example B5 = row B, seat number 5.
    // static so that main() can create Seat objects directly.
    // static class Seat {
    // final char row; // 'A' to 'E'
    // final int number; // 1 to 6

    // Seat(char row, int number) {
    // this.row = row;
    // this.number = number;
    // }

    // @Override
    // public String toString() {
    // return "" + row + number;
    // }

    // // TODO 1: override equals()
    // @Override
    // public boolean equals(Object o)
    // // public boolean equals(Seat o)
    // {

    // if (this == o) {
    // return true;
    // }

    // if (!(o instanceof Seat)) {
    // return false;
    // }

    // Seat other = (Seat) o;

    // return this.row == other.row &&
    // this.number == other.number;
    // }

    // // TODO 2: override hashCode()
    // @Override
    // public int hashCode() {
    // return Objects.hash(row, number);
    // }
    // }
    record Seat(char row, int number) {
    }

    public static void main(String[] args) {

        // Key = Seat, Value = name of the customer who booked it
        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A: book these seats using put()
        // A1 -> Ali A2 -> Sara B5 -> Omar C3 -> Mona
        // Print: Bookings: <the map>

        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");

        System.out.println("Bookings:           " + bookings);

        // Part B: who booked seat C3? Use get().
        // Print: Who booked C3: <name>

        String customer = bookings.get(new Seat('C', 3));

        System.out.println("Who booked C3:      " + customer);

        // Part C: are seats A2 and E6 taken? Use containsKey().
        // Print: Is A2 taken: <true or false>
        // Is E6 taken: <true or false>
        System.out.println("Is A2 taken:        " +
                bookings.containsKey(new Seat('A', 2)));

        System.out.println("Is E6 taken:        " +
                bookings.containsKey(new Seat('E', 6)));

        // Part D: Youssef wants seat B5. Use putIfAbsent(), so a booked seat is not
        // replaced.
        // putIfAbsent() returns null if the seat was free,
        // otherwise it returns the name that is already there.
        // Print: Youssef -> B5: booked
        // or: Youssef -> B5: refused, taken by <name>

        String previousCustomer = bookings.putIfAbsent(new Seat('B', 5), "Youssef");

        if (previousCustomer == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by "
                    + previousCustomer);
        }

        // Part E: Ali moves from seat A1 to seat D2. Use remove() and put().
        // Print: After Ali moved: <the map>

        bookings.remove(new Seat('A', 1));

        bookings.put(new Seat('D', 2), "Ali");

        System.out.println("After Ali moved:    " + bookings);

        // Part F: seats E1 and E2 are broken. Add them to the HashSet "broken".
        // Hana wants seat E1. Book it only if it is not broken. Use contains().
        // Print: Hana -> E1: booked
        // or: Hana -> E1: refused, seat is broken

        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));

        if (broken.contains(new Seat('E', 1))) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(new Seat('E', 1), "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        // Part G: how many seats are booked? Use size().
        // Print: Seats booked: <number>

        System.out.println("Seats booked:       " + bookings.size());

        // GIVEN - prints the seating chart
        System.out.println();
        printChart(bookings, broken);
    }

    // GIVEN - do not change. Prints the hall: X = booked # = broken . = free
    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}

/*
 * QUESTIONS - write your answers here
 * 
 * Q1.Why did we have to override hashcode() and equals()?
 * 
 * We override equals() to define when two Seat objects represent the same seat
 * based on their row and number.
 * We override hashCode() because HashMap and HashSet use it to determine where
 * objects are stored.
 * If two objects are equal according to equals(), they must have the same
 * hashCode().
 * 
 * Q2. Delete your hashCode() but keep equals(), and run again.
 * What happens? Why?
 * 
 * 
 * The program may produce incorrect results.Even though equals() considers two
 * Seat objects equal, they may have different hash codes because the default
 * hashCode() is still used.
 * HashMap and HashSet use hashCode() to locate objects, so they may fail to
 * find an existing seat.
 * This violates the equals() and hashCode() contract
 * 
 * 
 * 
 * Q3. Why are row and number final? What could go wrong if the number
 * of a booked seat could be changed after it was put in the map?
 * 
 * 
 * They are final to make the Seat object immutable.
 * If the row or number changes after inserting the object into a HashMap, its
 * hashCode() may change.
 * The HashMap may search in a different bucket than the one where the object
 * was originally stored, causing lookup problems
 * 
 * 
 * 
 * Q4. Change equals(Object o) to equals(Seat o). What happens?
 * Then also remove @Override and run again. What happens now?
 * 
 * 
 * First, if we change equals(Object o) to equals(Seat o) and keep @Override, a
 * compilation error occurs because the method signature does not match the
 * original equals(Object o) method in the Object class.
 * 
 * If we remove @Override, the program compiles, but the results become
 * incorrect because we created an overloaded method instead of overriding the
 * original equals() method. HashMap and HashSet still use the original
 * equals(Object o) method.
 * 
 * 
 * 
 * BONUS. Replace the whole Seat class with this one line:
 * record Seat(char row, int number) {}
 * Run it. What happens, and why?
 * 
 * 
 * The program works correctly and produces the expected output.
 * 
 * Java Records automatically provide a constructor, accessor methods, equals(),
 * hashCode(), and toString() methods.
 * 
 * Records are also immutable, so their fields cannot be changed after object
 * creation.
 * 
 * Therefore, we do not need to implement equals() and hashCode() manually.
 */
