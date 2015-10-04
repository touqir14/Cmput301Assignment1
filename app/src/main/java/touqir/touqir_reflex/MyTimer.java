package touqir.touqir_reflex;

import java.util.Random;

/**
 * Created by touqir on 27/09/15.
 */
public class MyTimer {

    private int waitPeriod;
    private long ReactionStartTime;
    private long userClickTime;
    private long latency;
    private Random randCreator= new Random();

    public MyTimer(){
    }

    private long getSysTime() {
        return System.currentTimeMillis( );
    }

    public void initReactionStartTime(){
        // initialTime is set to the current system time when the activity starts in milliseconds.
        ReactionStartTime=getSysTime();
    }

    public void initWaitPeriod() {
        // Initializes waiting period randomly. After waiting for this amount of millisecond
        // the app makes the "click me" text visible
        waitPeriod=this.randCreator.nextInt(1990)+10;
    }

    public void evaluateUserClickTime() {

        userClickTime=getSysTime();
    }

    public void evaluateLatency() {

        latency= userClickTime - ReactionStartTime;
    }

    public long getLatency() {
        try {
            return latency;
        } catch (Exception e) {

        }
        return 0;
    }


    public int getWaitPeriod(){
        try {
            return waitPeriod;
        } catch (Exception e){

        }
        return 0;
    }

}
