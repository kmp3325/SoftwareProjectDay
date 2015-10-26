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
            for (int j = 0; j < 4; j++) {
                Employee employee = new Employee(manager,time,managerOffice,main, j + 1, i + 1);
                teams.get(i).add(employee);
            }
        }
        time.start();
        // add all threads to a starting gate for a synchronous start
        manager.start();
        teams.forEach(t -> t.forEach(e -> e.start()));

    }
}