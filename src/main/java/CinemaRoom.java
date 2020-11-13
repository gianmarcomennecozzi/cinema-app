/**
 * This class create a new CinemaRoom and allow the user to book a seat. if the room capacity If the capacity is over
 * 50 people, the front half rows cost $12, the back half cost $10. If capacity 50 or below, all cost $10.
 */
public class CinemaRoom {
    private final int[][] seats;
    private final int capacity;
    private int firstHalf;
    private int purchasedTickets;
    private double income;
    private final double potentialIncome;
    private static final int LOW_COST_LIMIT = 50;

    static final double NORMAL_SEAT_PRICE = 10.00;
    static final double FRONT_SEAT_PRICE = 12.00;

    /**
     * Constructor for the class Cinema Room
     * @param x number of rows
     * @param y number of seats per row
     */
    public CinemaRoom(int x, int y){
        this.firstHalf = 0;
        this.purchasedTickets = 0;
        this.income = 0.00;

        this.seats = new int[x][y];
        this.capacity = x * y;

        //Calculate potential income
        //The first seats starting from the entrance are more expensive than the others
        //this works when the room capacity is higher than 50
        if (this.capacity >= LOW_COST_LIMIT && x > 1){
            this.firstHalf = (int) Math.ceil(x / 2.0);
            int secondHalf = x - firstHalf;
            this.potentialIncome = (y * firstHalf * FRONT_SEAT_PRICE) + (y * secondHalf * NORMAL_SEAT_PRICE);
        }else{
            this.potentialIncome = this.capacity * NORMAL_SEAT_PRICE;
            this.firstHalf = 0;
        }
    }

    /**
     * Book a seat
     * @param x the number of the row
     * @param y the number of the column
     */
    public boolean bookSeat(int x, int y){
        try {
            if (this.seats[x][y] == 1){
                throw new Exception();
            }else{
                this.seats[x][y] = 1;
                this.purchasedTickets++;

                if (this.firstHalf != 0 && x >= this.firstHalf){
                    this.income += NORMAL_SEAT_PRICE;
                }else{
                    this.income += FRONT_SEAT_PRICE;
                }
            }
            return true;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Error booking the seat! Insert valid numbers");
            return false;
        } catch (Exception e){
            System.out.println("Error booking the seat! Seat already booked");
            return false;
        }
    }

    public int[][] getSeats() {
        return this.seats;
    }

    public int getPurchasedTickets(){
        return this.purchasedTickets;
    }

    public double getIncome() {
        return this.income;
    }

    public double getPotentialIncome(){
        return this.potentialIncome;
    }

    public int getCapacity(){
        return this.capacity;
    }
}
