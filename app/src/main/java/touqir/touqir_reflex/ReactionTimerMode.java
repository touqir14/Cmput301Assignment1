package touqir.touqir_reflex;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Timer;

import touqir.touqir_reflex.Button.MyClickMe;
import touqir.touqir_reflex.Data.ReactionData;
import touqir.touqir_reflex.Dialog.ReactionT_DialogEarly;
import touqir.touqir_reflex.Dialog.ReactionT_DialogIntro;
import touqir.touqir_reflex.Dialog.ReactionT_DialogLater;

/**
 * Created by touqir on 30/09/15.
 */
public class ReactionTimerMode {

    private ReactionTimerActivity  hostActivity;
    private boolean pressedEarly, firstTime=true;
    private long latency;
    private int clickMeColour;
    private View.OnClickListener listenerEarly;
    private View.OnClickListener listenerLater;
    private MyClickMe<ReactionTimerActivity> clickMe;
    private RelativeLayout relativeLayout;
    private ReactionT_DialogEarly<ReactionTimerActivity> dialogEarly;
    private ReactionT_DialogLater<ReactionTimerActivity> dialogLater;
    private ReactionT_DialogIntro<ReactionTimerActivity> dialogIntro;
    private MyTimer myTimer= new MyTimer();
    private Handler reactionTimerHandler;


    public ReactionTimerMode(ReactionTimerActivity  activity) {

        hostActivity=activity;
    }

    public void startReactionTimerMode(){
        relativeLayout=(RelativeLayout) hostActivity.findViewById(R.id.ReactionTimer);
        reactionTimerHandler=new Handler();
        initDialogIntro();
        showDialogIntro();
    }

    private void dialogEarly_Yes_Handler(){
        //Handles the case when the user presses "Yes" button and thus the user is redirected to the reactionTimer page.
        reset();
    }

    private void dialogEarly_No_Handler(){
        //Handles the case when the user presses "No" button and thus the user is sent to the MainActivity page.
        destroy();
    }

    private void dialogLater_Restart_Handler(){
        //Handles the case when the user presses "Restart" button and thus the user is redirected to the reactionTimer page.
        reset();
    }

    private void dialogLater_Quit_Handler(){
        //Handles the case when the user presses "Quit" button and thus the user is sent to the MainActivity page.
        destroy();
    }

    private void initDialogsLaterEarly(){

        dialogEarly= new ReactionT_DialogEarly<ReactionTimerActivity >(hostActivity,
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogEarly_Yes_Handler();
                    }
                },
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogEarly_No_Handler();
                    }
                });

        dialogLater=new ReactionT_DialogLater<ReactionTimerActivity >(hostActivity,
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogLater_Restart_Handler();
                    }
                },
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogLater_Quit_Handler();
                    }
                });

    }

    private void dialogIntro_Continue_Handler(){
        //Handles the case when the user presses "continue" button and thus the user is sent to the reactionTimer page.
        reset();
    }

    private void dialogIntro_GoBack_Handler(){
        //Handles the case when the user presses "Go Back" button and thus the user is sent to the MainActivity page.
        destroy();
    }

    private void initDialogIntro(){
        dialogIntro = new ReactionT_DialogIntro<ReactionTimerActivity >(hostActivity,
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogIntro_Continue_Handler();//See the method for comments
                    }
                },
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogIntro_GoBack_Handler(); //See the method for comments
                    }
                });
    }

    private void reset(){
        // resets this activity's state to an initial state.
        // Called when the activity starts and also when the user clicks too early
        // and wants to retry.

        pressedEarly=false;
        initClickMe();
        myTimer.initWaitPeriod();
        configure_ReactionTime_Handler();
        initDialogsLaterEarly();
    }


    private void initClickMe() {
        clickMe=new MyClickMe<ReactionTimerActivity >(relativeLayout, hostActivity);
        clickMe.setListenerEarly(new Passable() {
            @Override
            public void invoker() {
                clickMeEarlyHandler();
            }
        });
        clickMe.setListenerLater(new Passable() {
            @Override
            public void invoker() {
                clickMeLaterHandler();
            }
        });
        clickMe.createMe();
    }

    public void configure_ReactionTime_Handler(){
        reactionTimerHandler.postDelayed(new Runnable() {
                                             @Override
                                             public void run() {
                                                activate_ClickMeLater_Handler();
                                             }
                                         },
                this.myTimer.getWaitPeriod());
    }

    private void showDialogIntro(){
        dialogIntro.showDialog();
    }

    private void showDialogEarly(){
        dialogEarly.showDialog();
    }

    private void showDialogLater(){
        dialogLater.showDialog();
    }

    private void activate_ClickMeLater_Handler(){
        clickMe.activateClickMeLater();
        myTimer.initReactionStartTime();
    }

    private void clickMeEarlyHandler() {
        // This is executed from inside clickMe.activateClickMeEarly
        pressedEarly=true;
        showDialogEarly();
    }

    private void showDialogWithLatency() {
        dialogLater.updateDialogMessage(latency);
        showDialogLater();
    }

    private void clickMeLaterHandler(){
        // This is executed from inside clickMe.activateClickMeLater
        updateLatency();
        saveLatency();
        showDialogWithLatency();
    }

    private void updateLatency(){
        this.myTimer.evaluateUserClickTime();
        this.myTimer.evaluateLatency();
        this.latency=this.myTimer.getLatency();
    }

    private void saveLatency(){
        ReactionData.saveData(hostActivity, this.latency);
    }

    private void destroy(){
        hostActivity.finish();
    }




}
