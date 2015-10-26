public class Metrics {

    private int timeSpentWorking;
    private int timeSpentAtLunch;
    private int timeSpentInMeetings;
    private int timeSpentWaitingForManager;
    private int timeSpentOnQuestions;

    public Metrics() {
        this.timeSpentWorking = 0;
        this.timeSpentAtLunch = 0;
        this.timeSpentInMeetings = 0;
        this.timeSpentWaitingForManager = 0;
        this.timeSpentOnQuestions = 0;
    }

    public synchronized void increaseWorkTime(int amt) {
        timeSpentWorking += amt;
    }

    public synchronized void increaseLunchTime(int amt) {
        timeSpentAtLunch += amt;
    }

    public synchronized void increaseMeetingTime(int amt) {
        timeSpentInMeetings += amt;
    }

    public synchronized void increaseWaitingForManagerTime(int amt) {
        timeSpentWaitingForManager += amt;
    }

    public synchronized void increaseQuestionTime(int amt) {
        timeSpentOnQuestions += amt;
    }

    private String format(int time) {
        return String.format("%01d hr(s) %02d min(s)", time / 60, time % 60);
    }

    public String toString() {
        return String.format("Working: %s --- Lunch: %s --- Meetings: %s --- Waiting: %s --- Questions: %s ---", format(timeSpentWorking), format(timeSpentAtLunch), format(timeSpentInMeetings), format(timeSpentWaitingForManager), format(timeSpentOnQuestions));
    }
}
