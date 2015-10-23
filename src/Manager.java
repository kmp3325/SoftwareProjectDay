import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Manager extends Thread {

    private final List<List<Employee>> teams;
    private final CountDownLatch managerOffice;
    private boolean busy;
    private Time time;
    public Manager(List<List<Employee>> teams,CountDownLatch latch, Time t)
    {
        this.teams = teams;
        this.managerOffice = latch;
        this.busy = false;
        this.time = t;
    }

    public List<List<Employee>> getTeams() {
        return teams;
    }

    public void run() {
        try {
        // print arrives at 8
            System.out.println(time.toString() + " Manager has arrived.");
            System.out.println(time.toString() + " Daily stand up meeting has started");
            morningStandUp();
            System.out.println(time.toString() + " Daily stand up meeting had ended");
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
            ;

            // while not 5
            //   wait
            // print leaves at 5
            System.out.println(time.toString() + " Manager engages in daily planning activities.");
            while(time.getTime() < 5400){
                Thread.sleep(10);
            }
            System.out.println(time.toString() + " Manager leaves.");


        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
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
    public synchronized void morningStandUp(){
        this.busy = true;
        try {
            wait(150);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
