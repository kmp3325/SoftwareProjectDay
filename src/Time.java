public class Time {

    private int time;
    private boolean meetingRoomOccupied;

    public Time() {
        this.time = 0;
        this.meetingRoomOccupied = false;
    }

    public int getTime(){
        return time;
    }

    public void incrementTime() {
        time++;
    }

    public synchronized void claimMeetingRoom(Employee employee) {
        System.out.println(this + " " + employee + " (the tech lead) is waiting for the meeting room for team stand-up.");
        while(meetingRoomOccupied) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        meetingRoomOccupied = true;
        System.out.println(this + " Team " + employee.getTeamNumber() + " stand-up meeting begins.");
        int endTime = time + 15;
        while (time < endTime) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        meetingRoomOccupied = false;
        System.out.println(this + " Team " + employee.getTeamNumber() + " stand-up meeting ends.");
    }

    @Override
    public String toString() {
        int hour = ((time / 60) + 8) % 13;
        if (hour < 8) hour++;
        return String.format("%01d:%02d", hour, time % 60);
    }
}
