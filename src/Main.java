import java.util.ArrayList;
import java.util.List;

public class Main {

    private long time;
    private boolean meetingRoomOccupied;

    public Main() {
        time = 0;
        this.meetingRoomOccupied = false;
    }

    public long getTime() {
        return time;
    }

    public synchronized void enterMeetingRoom(int teamNumber) {
        // while meeting room is occupied
        //   wait
        // meetingRoomOccupied = true
        // while not end of meeting (15 mins)
        //   wait
        // print "end of meeting for team X"
    }

    public static void main(String[] args) {
        Main main = new Main();

        List<List<Employee>> teams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            teams.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                Employee employee = new Employee(main, j, i);
                teams.get(i).add(employee);
            }
        }

        Manager manager = new Manager(teams);
        // add all threads to a starting gate for a synchronous start
        manager.start();
        manager.getTeams().forEach(t -> t.forEach(e -> e.start()));
        //
        // while not end of day
        //   time+=10
        //   sleep 10
        //   notifyAll
    }
}