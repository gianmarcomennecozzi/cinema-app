/**
 * This class create a new CinemaRoom and allow the user to book a seat. if the room capacity If the capacity is over
 * 50 people, the front half rows cost $12, the back half cost $10. If capacity 50 or below, all cost $10.
 */
public class CinemaRoom {
    private final String name; //todo check what final and static means
    private int[][] seats;
    private int capacity;
    private int firstHalf;
    private int purchasedTickets;
    private double income;
    private double potentialIncome;

    static final double NORMAL_SEAT_PRICE = 10.00;
    static final double FRONT_SEAT_PRICE = 12.00;

    /**
     * Constructor for the class Cinema Room
     * @param name of the Cinema Room
     * @param x number of rows
     * @param y number of seats per row
     */
    public CinemaRoom(String name, int x, int y){
        this.name = name;
        this.firstHalf = 0;
        this.purchasedTickets = 0;
        this.income = 0.00;

        this.seats = new int[x][y];
        this.capacity = x * y;

        if (this.capacity >= 50){
            this.firstHalf = (int) Math.ceil(x / 2.0);
            int secondHalf = x - firstHalf;
            this.potentialIncome = (firstHalf * FRONT_SEAT_PRICE) + (secondHalf * NORMAL_SEAT_PRICE);
        }else{
            this.potentialIncome = this.capacity * NORMAL_SEAT_PRICE;
            this.firstHalf = 0;
        }
    }

    /**
     * Prints the following metrics:
     *  - Number of purchased tickets
     *  - Percentage occupied
     *  - Current income (sum of reserved tickets)
     *  - Potential total income (sum of all available and reserved tickets)
     */
    public void showMetrics(){
        System.out.printf("\nNumber of purchased tickets: %d", this.purchasedTickets);
        System.out.printf("\nPercentage occupied: %.2f", (this.purchasedTickets * 100.00) / this.capacity);
        System.out.printf("\nCurrent income: $ %.2f", this.income);
        System.out.printf("\nPotential total income: $ %.2f \n", this.potentialIncome);
    }

    /**
     * Book a seat
     * @param x the number of the row
     * @param y the number of the column
     */
    public void bookSeat(int x, int y){
        try {
            if (this.seats[x][y] == 1){
                throw new Exception();
            }else{
                this.seats[x][y] = 1;
                this.purchasedTickets++;

                if (this.firstHalf != 0 && x >= this.firstHalf){
                    this.income += FRONT_SEAT_PRICE;
                }else{
                    this.income += NORMAL_SEAT_PRICE;
                }
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Error booking the seat! Insert valid numbers");
        } catch (Exception e){
            System.out.println("Error booking the seat! Seat already booked");
        }
    }

    /**
     * Print seats
     */
    public void printSeat() {
        System.out.println("\nROOM ENTRANCE HERE");
        System.out.println("------------------");
        for (int i = 0; i < this.seats[0].length; i++) {
            System.out.print("\tC" + i);
        }
        System.out.println();
        for (int i = 0; i < this.seats.length; i++) {
            System.out.print("R" + i + "\t");
            for (int j = 0; j < this.seats[0].length; j++) {
                if (this.seats[i][j] == 0){
                    System.out.print("A\t");
                }else {
                    System.out.print("R\t");
                }
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("----SCREEN HERE---");
    }
}
