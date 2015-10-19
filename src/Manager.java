import java.util.List;

public class Manager extends Thread {

    private final List<List<Employee>> teams;

    public Manager(List<List<Employee>> teams) {
        this.teams = teams;
    }

    public List<List<Employee>> getTeams() {
        return teams;
    }

    public void run() {

    }
}
