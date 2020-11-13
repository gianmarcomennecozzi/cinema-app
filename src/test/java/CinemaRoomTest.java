import junit.framework.TestCase;

public class CinemaRoomTest extends TestCase {

    private final int X = 20;
    private final int Y = 15;

    public void testBookSeat() {
        CinemaRoom room = new CinemaRoom(X, Y);
        assertTrue(room.bookSeat(0,0));
        assertFalse(room.bookSeat(0,0));
    }

    public void testGetSeats() {
        CinemaRoom room = new CinemaRoom(X, Y);
        int[][] seats = room.getSeats();
        assertEquals(seats.length, X);
        assertEquals(seats[0].length, Y);
    }

    public void testGetPurchasedTickets() {
        CinemaRoom room = new CinemaRoom(X, Y);
        int bookedSeats = 10;
        for (int i = 0; i < bookedSeats; i++) {
            room.bookSeat(i, 0);
        }
        assertEquals(room.getPurchasedTickets(), bookedSeats);
    }

    public void testGetIncome() {
        CinemaRoom room = new CinemaRoom(X, Y);
        room.bookSeat(0,0);
        assertEquals(room.getIncome(), CinemaRoom.FRONT_SEAT_PRICE);
        room.bookSeat(10,0);
        assertEquals(room.getIncome(), CinemaRoom.FRONT_SEAT_PRICE + CinemaRoom.NORMAL_SEAT_PRICE);
    }

    public void testGetPotentialIncome() {
        CinemaRoom room = new CinemaRoom(X, Y);

        double firstHalf = CinemaRoom.FRONT_SEAT_PRICE * 10 * Y;
        double secondHalf = CinemaRoom.NORMAL_SEAT_PRICE * 10 * Y;
        assertEquals(room.getPotentialIncome(), firstHalf + secondHalf);
    }

    public void testGetCapacity() {
        CinemaRoom room = new CinemaRoom(X, Y);
        assertEquals(room.getCapacity(), 300);
    }
}