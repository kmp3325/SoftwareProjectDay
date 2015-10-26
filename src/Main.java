import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        final Time time = new Time();
        final CountDownLatch morningStandUp = new CountDownLatch(3);
        final CountDownLatch afternoonStandUp = new CountDownLatch(3);
        List<List<Employee>> teams = new ArrayList<>();
        Manager manager = new Manager(teams, morningStandUp, afternoonStandUp, time, new Metrics());

        for (int i = 0; i < 3; i++) {
            final CountDownLatch teamStandUp = new CountDownLatch(3);
            teams.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                Employee employee = new Employee(j, i, time, morningStandUp, afternoonStandUp, teamStandUp, manager, new Metrics());
                teams.get(i).add(employee);
            }
        }

        manager.start();
        manager.getTeams().forEach(t -> t.forEach(e -> e.start()));

        while (time.getTime() <= 600) {
            synchronized (time) {
                time.notifyAll();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time.incrementTime();
        }
        System.out.println("-----------------------------------METRICS-----------------------------------");
        System.out.println("Manager - " + manager.getMetrics());
        teams.forEach(t -> t.forEach(e -> {
            System.out.println(e + " - " + e.getMetrics());
        }));
    }
}