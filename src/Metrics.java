public class Metrics {

    private volatile int timeSpentWorking;
    private volatile int timeSpentAtLunch;
    private volatile int timeSpentInMeetings;
    private volatile int timeSpentWaitingForManager;
    private volatile int timeSpentOnQuestions;
    private volatile int numberOfQuestions;

    public Metrics() {
        this.timeSpentWorking = 0;
        this.timeSpentAtLunch = 0;
        this.timeSpentInMeetings = 0;
        this.timeSpentWaitingForManager = 0;
        this.timeSpentOnQuestions = 0;
        this.numberOfQuestions = 0;
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

    public synchronized void increaseNumberOfQuestions(int amt) {
        numberOfQuestions += amt;
    }

    private String format(int time) {
        return String.format("%01d hr(s) %02d min(s)", time / 60, time % 60);
    }

    public String toString() {
        return String.format("Total Paid Time: %s --- Working: %s --- Lunch: %s --- Meetings: %s --- Waiting: %s --- Questions: %s --- Number of Questions: %s",
                format(timeSpentWorking + timeSpentInMeetings + timeSpentWaitingForManager + timeSpentOnQuestions),
                format(timeSpentWorking),
                format(timeSpentAtLunch),
                format(timeSpentInMeetings),
                format(timeSpentWaitingForManager),
                format(timeSpentOnQuestions),
                numberOfQuestions);
    }
}
