import java.util.concurrent.CountDownLatch;

public class Employee extends Thread {

    private final int number;
    private final int teamNumber;
    private final Time time;
    private final CountDownLatch morningStandUp;
    private final CountDownLatch afternoonStandUp;
    private final CountDownLatch teamStandUp;
    private final Manager manager;
    private volatile boolean occupied;
    private volatile boolean morningStandUpComplete;
    private volatile boolean afternoonStandUpComplete;
    private volatile boolean teamStandUpComplete;

    public Employee(int number, int teamNumber, Time time, CountDownLatch morningStandUp, CountDownLatch afternoonStandUp, CountDownLatch teamStandUp, Manager manager) {
        this.number = number;
        this.teamNumber = teamNumber;
        this.time = time;
        this.morningStandUp = morningStandUp;
        this.afternoonStandUp = afternoonStandUp;
        this.teamStandUp = teamStandUp;
        this.manager = manager;
        this.occupied = false;
        this.morningStandUpComplete = false;
        this.afternoonStandUpComplete = false;
        this.teamStandUpComplete = false;
    }

    public void run() {
        int arrivalTime = (int)(Math.random() * 30);
        waitUntil(arrivalTime);
        System.out.println(time + " " + this + " has arrived.");

        if (number == 0) {
            morningStandUp.countDown();
            waitUntilStandUpComplete("Morning");
            occupied = true;
            try {
                teamStandUp.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            standUp();
        } else {
            teamStandUp.countDown();
            waitUntilStandUpComplete("Team");
        }

        workUntil(240);

        System.out.println(time + " "+ this + " goes on lunch break.");
        int lunchTime = (int)(Math.random() * (30 - arrivalTime)) + 30;
        busyUntil(lunchTime + 240);
        System.out.println(time + " "+ this + " returns from lunch.");

        if (number == 0) {
            workUntil(480);
            System.out.println(time + " " + this + " arrives at managers office for afternoon stand-up.");
            afternoonStandUp.countDown();
            waitUntilStandUpComplete("Afternoon");
        }

        int endOfDay = arrivalTime + lunchTime + 480;
        workUntil(endOfDay);
        System.out.println(time + " " + this + " leaves for the end of the day.");
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public synchronized void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public synchronized void setStandUpDone(String timeOfDay) {
        switch(timeOfDay) {
            case "Morning":
                this.morningStandUpComplete = true;
                break;
            case "Afternoon":
                this.afternoonStandUpComplete = true;
                break;
            default:
                this.teamStandUpComplete = true;
        }
    }

    public boolean getStandUpComplete(String timeOfDay) {
        switch(timeOfDay) {
            case "Morning":
                return morningStandUpComplete;
            case "Afternoon":
                return afternoonStandUpComplete;
            default:
                return teamStandUpComplete;
        }
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

    private void waitUntil(int endTime) {
        synchronized (time) {
            while (time.getTime() < endTime || occupied) {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void workUntil(int endTime) {
        while (time.getTime() < endTime || occupied) {
            work();
        }
    }

    private void waitUntilStandUpComplete(String timeOfDay) {
        occupied = true;
        synchronized (time) {
            while (!getStandUpComplete(timeOfDay)) {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void work() {
        synchronized (time) {
            if (Math.random() > .999 && !occupied) {
                occupied = true;
                System.out.println(time + " " + this + " has a question.");
                if (number == 0) {
                    manager.askQuestion(this);
                } else {
                    manager.getTeams().get(teamNumber).get(0).askQuestion(this);
                }
                occupied = false;
            } else {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void askQuestion(Employee employee) {
        synchronized (time) {
            System.out.println(time + " " + employee + " is waiting for tech lead to answer question.");
            while (occupied) {
                try {
                    time.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            occupied = true;
            if (Math.random() > .5) {
                System.out.println(time + " tech lead answered " + employee + "'s question.");
            } else {
                System.out.println(time + " tech lead cannot answer " + employee + "'s question, going to manager.");
                manager.askQuestion(employee);
            }
            occupied = false;
        }
    }

    private void standUp() {
        System.out.println(time + " Team" + teamNumber + " is waiting for meeting room for team stand-up.");
        time.claimMeetingRoom(this);
        occupied = false;
        manager.getTeams().get(teamNumber).forEach(e -> {
            e.setStandUpDone("Team");
            e.setOccupied(false);
        });
    }

    public String toString() {
        return String.format("Employee %d%d", teamNumber, number);
    }
}
