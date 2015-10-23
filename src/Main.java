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

    public synchronized void enterMeetingRoom(int teamNumber) {
        while (meetingRoomOccupied) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stand-up for team "+teamNumber+" has begun.");
        meetingRoomOccupied = true;
        //long endOfMeeting = t + 15;
        //while (time < endOfMeeting) {
          //  try {
            //    wait();
            //} catch (InterruptedException e) {
              //  e.printStackTrace();
            //}
        //}
        System.out.println("Stand-up has finished for team "+teamNumber+".");
        // while meeting room is occupied
        //   wait
        // meetingRoomOccupied = true
        // while not end of meeting (15 mins)
        //   wait
        // print "end of meeting for team X"
    }

    public static void main(String[] args) {
        Main main = new Main();
        final CountDownLatch managerOffice = new CountDownLatch(3);
        List<List<Employee>> teams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            teams.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                Employee employee = new Employee(time,managerOffice,main, j + 1, i + 1);
                teams.get(i).add(employee);
            }
        }
        time.start();
        long currentTime = time.getTime();
        Manager manager = new Manager(teams, managerOffice,time);
        // add all threads to a starting gate for a synchronous start
        manager.start();
        manager.getTeams().forEach(t -> t.forEach(e -> e.start()));


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