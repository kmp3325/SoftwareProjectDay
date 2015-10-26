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
    private Manager manager;

    public Employee(Manager m, Time t,CountDownLatch standUpLatch, Main main, int number, int teamNumber) {
        this.main = main;
        this.number = number;
        this.teamNumber = teamNumber;
        this.occupied = true;
        this.lunchBreakTime = 0;
        this.arrived = false;
        this.standUpLatch = standUpLatch;
        this.time = t;
        this.manager = m;
    }


        public void run () {
            try {
                // determine arrive time (8-830)
                long endDay = time.getEndDay();
                int arrivalTime = (int) (Math.random() * 300 + 1);
                int maxDuration = 300 - arrivalTime;
                int leaveTime = arrivalTime + 4840;
                int lunchDuration = (int) (Math.random() * maxDuration + 1);
                int lunchTime = (int) (Math.random() * 300 + 1);
                lunchDuration = lunchDuration + 300;
                lunchTime = 2400 + lunchTime;
                sleep(arrivalTime);
                // print arrived
                System.out.println(this.toString() + " has arrived.");
                // arrived = true
                this.arrived = true;
                if (this.number == 1) {
                    standUpLatch.countDown();
                    this.occupied = true;

                }
                //   managerStandUp
                //   standUp
                // determine lunch time

                while (time.getTime() < 5400) {

                    while (time.getTime() <= lunchTime) {
                        work();

                    }
                    System.out.println(this.toString() + " has left for lunch.");
                    Thread.sleep(lunchDuration);
                    System.out.println(this.toString() + " has returned from lunch");

                    while (time.getTime() <= leaveTime) {
                        work();
                        //Thread.sleep(10);
                    }

                    System.out.println(this.toString() + " has left for the day");
                    break;

                    }
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
        int questionChance = (int) (Math.random() * 1000 + 1);
        if(questionChance == 9){
            this.occupied = true;
            System.out.println(this.toString() +  " has a question");
            askQuestion(this.number);
        }

        else{
            try {
                Thread.sleep(10);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }

    }

    public synchronized void askQuestion(int employeeNumber) {
        int answerChance = (int) (Math.random() * 2 + 1);
        if(answerChance == 1){
            System.out.println(time.toString() + " Tech lead answers for Employee" +
                teamNumber + number);
        }
        else{
            this.manager.askQuestion(this);
        }
        this.occupied = false;
    }

    @Override
    public String toString(){
        String s = time.toString() + " Employee" + teamNumber
                + number;
        return s;
    }
}
