import java.time.Clock;

/**
 * Created by Kevin on 10/21/2015.
 */public class Time {

    private long startTime;
    private long elapsedTime;
    private long endDay;
    private int hour;
    private int minutes;

    public Time(){

        this.startTime = 0;
        this.elapsedTime = 0;
        this.endDay = 0;
        this.hour = 8;
        this.minutes = 0;
    }

    public void start(){

        startTime = System.currentTimeMillis();
        endDay = startTime + 5400;
    }

    public long getTime(){
        elapsedTime = System.currentTimeMillis() - startTime;
        return elapsedTime;
    }

    @Override
    public String toString(){
        getTime();
        minutes = (int) elapsedTime / 10;
        int elapsedHours = minutes/60;
        minutes = (int) minutes - (elapsedHours * 60);
        int otherHour = elapsedHours + hour;
        String s = String.format("%01d:%02d", otherHour, minutes);
        //elapsedHours = 0;
        minutes = 0;
        return s;
    }

    public long getEndDay(){
        return endDay;
    }

}
