import java.util.List;

public class Manager extends Thread {

    private final List<List<Employee>> teams;

    public Manager(List<List<Employee>> teams) {
        this.teams = teams;
    }

    public List<List<Employee>> getTeams() {
        return teams;
    }

    public void run() {
        // print arrives at 8
        // while not 10 and not occupied
        //   wait
        // occupied = true
        // while not 11
        //   wait
        // occupied = false
        // determine lunch
        // while not lunch and not occupied
        //   wait
        // occupied = true
        // while not end of lunch
        //   wait
        // occupied = false
        // while not 2 and not occupied
        //   wait
        // occupied = true
        // while not 3
        //   wait
        // occupied = false
        // while not 5
        //   wait
        // print leaves at 5
    }

    public synchronized void askQuestion(int employeeNumber) {
        // while occupied
        //   wait
        // occupied = true
        // while not end of 10 mins
        //   wait
        // print "Manager finishes answering employee X's question"
        // occupied = false
    }
}
