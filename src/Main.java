import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private long time;
    private Set<Thread> inMeeting; //TODO meeting room is only used for team meetings...... :( need to wait on manager for all other meetings
    private List<Integer> meetingQueue;
    private Set<Thread> waitingForMeeting;
    private int meetingIdCounter;

    public Main() {
        time = 0;
        this.inMeeting = new HashSet<>();
        this.meetingQueue = new ArrayList<>();
        this.waitingForMeeting = new HashSet<>();
        this.meetingIdCounter = 0;
    }

    public long getTime() {
        return time;
    }

    public void meeting(String meetingName, int meetingId, int meetingLength, List<Thread> toMeet) {
        // print "A meetingName meeting has been called."
        // meetingQueue.add(meetingId)
        // while waitingForMeeting does not contain all in toMeet
        //   add any not occupied to waiting
        //   occupied = true
        //   print person is waiting for meetingName to start
        //   inc time
        // add each toMeet to inMeeting
        // remove all toMeet from waitingForMeeting
        // remove meetingId from queue
        // print "meetingName meeting begins."
        // start = time
        // while time - start < meetingLength
        //   inc time
        // all occupied = false
        // remove all from inMeeting
        // print meeting ends
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
        // meeting("Manager Stand-up", meetingIdCounter, 150, all tech leads and manager)
        // meetingIdCounter++
        // for each team
        //   meeting("Team "+i+" Stand-up", meetingIdCounter, 150, all members of team)
        //   meetingIdCounter++
        // while not end of day
        //   if question (random chance of like a fraction of a % or something)
        //     find random employee not occupied (or skip if all are occupied)
        //     print employee has question
        //     if tech lead or tech lead cannot answer
        //       meeting("Question", meetingIdCounter, 100, questionAsker+tech lead+manager)
        //       meetingIdCounter++
        //     else
        //       print question answered by tech lead
        //   inc time
    }
}