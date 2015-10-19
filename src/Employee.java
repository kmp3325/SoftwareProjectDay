public class Employee extends Thread {

    private final Main main;
    private final int number;
    private final int teamNumber;
    private boolean occupied;
    private long lunchBreakTime;

    public Employee(Main main, int number, int teamNumber) {
        this.main = main;
        this.number = number;
        this.teamNumber = teamNumber;
        this.occupied = true;
        this.lunchBreakTime = 0;
    }

    public void run() {
        // determine arrive time (8-830)
        // while not arrival time
        //   wait
        // print arrived
        // occupied = false
        // while not lunch and not occupied
        //   wait
        // lunch break (occupied)
        // determine end of day
        // while not end of day and not occupied
        //   wait
    }

    public int getNumber() {
        return number;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public boolean getOccupied() {
        return occupied;
    }
}
