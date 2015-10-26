import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Manager extends Thread {

    private final List<List<Employee>> teams;
    private final CountDownLatch morningStandUp;
    private final CountDownLatch afternoonStandUp;
    private final Time time;
    private final Metrics metrics;
    private volatile boolean occupied;

    public Manager(List<List<Employee>> teams, CountDownLatch morningStandUp, CountDownLatch afternoonStandUp, Time time, Metrics metrics) {
        this.teams = teams;
        this.morningStandUp = morningStandUp;
        this.afternoonStandUp = afternoonStandUp;
        this.time = time;
        this.metrics = metrics;
        this.occupied = false;
    }

    public List<List<Employee>> getTeams() {
        return teams;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void run() {
        System.out.println(time + " Manager has arrived.  Managerial tasks will be performed until all tech leads arrive.");
        occupied = true;
        try {
            morningStandUp.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } metrics.increaseWorkTime(time.getTime());

        standUp("Morning");

        workUntil(120);

        meeting("10:00", 180);

        workUntil(240);

        System.out.println(time + " Manager takes lunch."); int startTimeForMetrics = time.getTime();
        busyUntil(300);
        System.out.println(time + " Manager returns from lunch."); metrics.increaseLunchTime(time.getTime() - startTimeForMetrics);

        workUntil(360);

        meeting("2:00", 420);

        workUntil(480);
        System.out.println(time + " Manager is waiting for status updates from tech leads.  Doing managerial work until they all show up."); startTimeForMetrics = time.getTime();
        try {
            afternoonStandUp.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } metrics.increaseWorkTime(time.getTime() - startTimeForMetrics);

        standUp("Afternoon");

        workUntil(540);
        System.out.println(time + " Manager leaves.");
    }

    private void busyUntil(int endTime) {
        synchronized (time) {
            occupied = true;
            while (time.getTime() < endTime) {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            occupied = false;
        }
    }

    private void workUntil(int endTime) {
        while (time.getTime() < endTime || occupied) {
            work();
        }
    }

    private void standUp(String timeOfDay) {
        System.out.println(time + " " + timeOfDay + " stand-up meeting begins.");
        int endTime = time.getTime() + 15;
        busyUntil(endTime);
        System.out.println(time + " " + timeOfDay + " stand-up meeting ends.");
        occupied = false;
        teams.forEach(t -> {
            t.get(0).setOccupied(false);
            t.get(0).setStandUpDone(timeOfDay);
        }); metrics.increaseMeetingTime(15);
    }

    private void work() {
        synchronized (time) {
            try {
                time.wait(); if (!occupied) metrics.increaseWorkTime(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void meeting(String timeAsString, int endTime) {
        System.out.println(time + " Manager enters " + timeAsString + " meeting."); int startTimeForMetrics = time.getTime();
        busyUntil(endTime);
        System.out.println(time + " Manager leaves " + timeAsString + " meeting."); metrics.increaseMeetingTime(endTime - startTimeForMetrics);
    }

    public void askQuestion(Employee employee) {
        synchronized (time) {
            while (occupied) {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(time + " Manager meets about " + employee + "'s question.");
            int endTime = time.getTime() + 10;
            busyUntil(endTime);
            System.out.println(time + " Manager finishes answering " + employee + "'s question."); metrics.increaseQuestionTime(10);
        }
    }
}
