import java.util.concurrent.CountDownLatch;

public class Employee extends Thread {

    private final Main main;
    private final int number;
    private final int teamNumber;
    private boolean occupied;
    private long lunchBreakTime;
    private boolean arrived;
    private Time time;
    private CountDownLatch standUpLatch;

    public Employee(Time t,CountDownLatch standUpLatch, Main main, int number, int teamNumber) {
        this.main = main;
        this.number = number;
        this.teamNumber = teamNumber;
        this.occupied = true;
        this.lunchBreakTime = 0;
        this.arrived = false;
        this.standUpLatch = standUpLatch;
        this.time = t;
    }


        public void run () {
            try{
                // determine arrive time (8-830)
                long endDay = time.getEndDay();
                int arrivalTime = (int) (Math.random() * 300 + 1);
                int leaveTime = arrivalTime + 4800;
                sleep(arrivalTime);

                // print arrived
                System.out.println(time.toString() + " Employee" + teamNumber
                        + number + " has arrived.");
                // arrived = true
                if (this.number == 1) {
                    standUpLatch.countDown();
                }
                //   managerStandUp
                //   standUp
                // determine lunch time
                int lunchDuration = (int) (Math.random() * 600 + 1);
                while(time.getTime() < 5400) {

                    if(time.getTime() >= leaveTime){
                        System.out.println(time.toString() + " Employee" + teamNumber
                                + number + " has left for the day");
                        break;
                    }
                    else{
                        Thread.sleep(10);
                    }
                }

                //while not lunch and not occupied
                //   wait
                // lunch break (occupied)
                // determine end of day
                // while not end of day and not occupied
                //   wait
                // print this employee leaves
            }catch(InterruptedException ie){
                ie.printStackTrace();
                }
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

    public void managerStandUp() {
        // while all team leads have not arrived
        //   wait
        // print "team lead for team X joins manager stand up"
        // while not end of manager standup (15 mins)
        //   wait
        // print "team lead for team X leaves manager stand up"
    }

    public void standUp() {
        // print "Lead for team teamNumber is waiting for full team to start stand-up."
        // while all team members have not arrived
        //   wait
        // enterMeetingRoom
        // set all team members to occupied = false
    }

    public void work() {
        // if has question (some really low percentage)
        //   occupied = true
        //   print "has a question"
        //   tech lead.askQuestion()
        // else
        //   wait
    }

    public synchronized void askQuestion(int employeeNumber) {
        // while occupied
        //   wait
        // occupied = true
        // if can answer
        //   print "tech lead answers X's question"
        // else
        //   print "tech lead cannot answer question, must ask manager"
        //   manager.askQuestion()
        // occupied = false
    }
}
