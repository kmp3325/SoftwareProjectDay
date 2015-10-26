import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    private static Time time;
    private boolean meetingRoomOccupied;

    public Main() {
        this.meetingRoomOccupied = false;
        this.time = new Time();
    }


    public static void main(String[] args) {
        Main main = new Main();
        final CountDownLatch managerOffice = new CountDownLatch(3);
        List<List<Employee>> teams = new ArrayList<>();

        Manager manager = new Manager(managerOffice,time);
        for (int i = 0; i < 3; i++) {
            teams.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                Employee employee = new Employee(manager,time,managerOffice,main, j + 2, i + 2);
                teams.get(i).add(employee);
            }
        }
        time.start();
        long currentTime = time.getTime();
        // add all threads to a starting gate for a synchronous start
        manager.start();
        teams.forEach(t -> t.forEach(e -> e.start()));


        while (currentTime < 540) {
            currentTime = time.getTime();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (main) {
                main.notifyAll();
            }
        }
        //
        // while not end of day
        //   time+=10
        //   sleep 10
        //   notifyAll
    }
}