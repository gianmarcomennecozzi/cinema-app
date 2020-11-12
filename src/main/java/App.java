import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class manage the cinema rooms
 */
public class App {

    private final HashMap<String, CinemaRoom> rooms;
    private final Scanner scanner;
    private CinemaRoom currentRoom;


    public App(){
        this.rooms = new HashMap<String, CinemaRoom>();
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        boolean exit = false;
        int n = 0;
        printMenu();
        do{
            System.out.print("Select an action: ");
            try {
                n = this.scanner.nextInt();
                switch (n) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        this.createRoom();
                        break;
                    case 2:
                        this.currentRoom = this.selectRoom();
                        break;
                    case 3:
                        if (this.currentRoom != null){
                            this.currentRoom.printSeat();
                        }else {
                            System.out.println("Error! Select a cinema room first");
                        }
                        break;
                    case 4:
                        this.bookSeat(this.currentRoom);
                        break;
                    case 5:
                        if (this.currentRoom != null){
                            this.currentRoom.showMetrics();
                        }else {
                            System.out.println("Error! Select a cinema room first");
                        }
                        break;
                    case 6:
                        printMenu();
                        break;
                    default:
                        throw new Exception();
                }
            }catch (Exception e){
                this.scanner.nextLine();
                System.out.println("Enter a valid action value");
            }
        }while(!exit);
    }

    /**
     * Allow the user to insert cinema room name and the it size (row * col)
     */
    private void createRoom() {
        boolean exit = false;
        int rows = 0;
        int cols = 0;
        System.out.print("Insert a name for the new cinema room: ");
        String name = this.scanner.next();
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);

        if (m.find()){
            System.out.println("Error! Insert a name without special character");
            return;
        }

        if (this.rooms.containsKey(name)){
            System.out.println("Room name already exists. Choose a different one!");
            return;
        }

        System.out.println("Each room has row * col number of seats");
        do{
            try {
                System.out.print("Insert number of rows: ");
                rows = this.scanner.nextInt();
                System.out.print("Insert number of cols: ");
                cols = this.scanner.nextInt();
                exit = true;
            }catch (Exception e){
                this.scanner.nextLine();
                System.out.println("Error! Enter valid numbers!");
            }
        }while (!exit);

        if (cols == 0 || rows == 0){
            System.out.println("Error! The number of rows and columns have to be higher than 0");
            return;
        }

        CinemaRoom room = new CinemaRoom(name, rows, cols);
        this.rooms.put(name, room);
        System.out.println("Room created!");
    }

    /**
     * Allow the user to insert the row and col for a specific seat
     * @param room selected from the user
     */
    private void bookSeat(CinemaRoom room){
        boolean exit = false;
        int r = 0;
        int c = 0;
        if (room == null){
            System.out.println("Error! Select a cinema room first");
            return;
        }

        do{
            try {
                System.out.print("Insert the seat row: ");
                r = this.scanner.nextInt();
                System.out.print("Insert the column row: ");
                c = this.scanner.nextInt();
                exit = true;
            }catch (Exception e){
                this.scanner.nextLine();
                System.out.println("Error! Enter valid numbers!");
            }
        }while (!exit);

        room.bookSeat(r, c);

    }

    /**
     * Allow the user to select a room from the list
     * @return CinemaRoom or null depending the input of the user
     */
    public CinemaRoom selectRoom(){
        System.out.printf("Select the Room you want to manage from this list %s: ", this.rooms.keySet());
        String name = this.scanner.next();
        CinemaRoom room = this.rooms.get(name);
        if (room == null){
            System.out.println("Error selecting the room");
        }
        return room;
    }

    private static void printMenu(){
        System.out.println("\nAllowed Actions");
        System.out.println("-------------------------");
        System.out.println("1 - Create a new cinema room");
        System.out.println("2 - Select a cinema room");
        System.out.println("3 - Show seats status");
        System.out.println("4 - Buy ticket");
        System.out.println("5 - Show cinema room metrics");
        System.out.println("6 - Print action menu");
        System.out.println("0 - Exit\n");
    }
}
