import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Manager extends Thread {


    private final CountDownLatch managerOffice;
    private boolean busy;
    private Time time;
    public Manager(CountDownLatch latch, Time t)
    {

        this.managerOffice = latch;
        this.busy = false;
        this.time = t;
    }

    public void run() {
        try {
        // print arrives at 8
            System.out.println(time.toString() + " Manager has arrived.");
            managerOffice.await();
            System.out.println(time.toString() + " Daily stand up meeting has started");
            morningStandUp();
            System.out.println(time.toString() + " Daily stand up meeting had ended");
            // while not 10 and not occupied
            while(time.getTime() < 1200){
                Thread.sleep(10);
            }
            if(this.busy == false){
                executiveMeeting10();
            }else{
                //TODO finish whatever manager is doing, then go to meeting.
            }
            while (time.getTime() < 2400) {
                Thread.sleep(10);
            }
            if(this.busy == false) {

                System.out.println(time.toString() + " Manager has left for lunch.");
                this.busy = true;
                Thread.sleep(600);
                System.out.println(time.toString() + " Manager has returned from lunch.");
                this.busy = false;
            }else{
                //TODO finish whatever he is doing, then go to lunch
            }
            while(time.getTime() < 3600 ){
                Thread.sleep(10);
            }
            if(this.busy == false) {
                executiveMeeting2();
            }else{
                //TODO finish whatever he is doing, then go to meeting.
            }
            System.out.println(time.toString() + " Manager engages in daily planning activities.");
            while(time.getTime() < 5400){
                Thread.sleep(10);
            }
            System.out.println(time.toString() + " Manager leaves.");


        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }


    public synchronized void askQuestion(Employee e) {
        // while occupied
        try {
            while(this.busy){
                e.wait(10);
                wait(10);
            }
        this.busy = true;
        wait(100);
        e.wait(10);
        System.out.println(time.toString() + " Manager answers question for " +
            e.toString());

        // occupied = false
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
    public synchronized void morningStandUp(){
        this.busy = true;
        try {
            wait(150);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        this.busy = false;
    }

    public synchronized void executiveMeeting10(){
        this.busy = true;
        System.out.println(time.toString() + " Executive meeting has started.");
        while(time.getTime() <= 1800){
            try {
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(time.toString() + " Executive meeting has ended.");
        this.busy = false;
    }
    public synchronized void executiveMeeting2(){
        this.busy = true;
        System.out.println(time.toString() + " Executive meeting has started.");
        while(time.getTime() <= 4200){
            try {
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(time.toString() + " Executive meeting has ended.");
        this.busy = false;

    }
}
