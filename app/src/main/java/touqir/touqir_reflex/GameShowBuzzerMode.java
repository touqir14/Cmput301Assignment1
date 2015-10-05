package touqir.touqir_reflex;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;

import touqir.touqir_reflex.Button.MyButtonBuzzer;
import touqir.touqir_reflex.Data.BuzzerData;
import touqir.touqir_reflex.Dialog.GameShowB_Dialog;

/**
 * Created by touqir on 04/10/15.
 */
public class GameShowBuzzerMode {

    private Activity myActivity;
    private RelativeLayout myLayout;
    private int playerNumber;
    private MyButtonBuzzer buttonBuzzer;
    private GameShowB_Dialog dialog;
    private Boolean alreadyClicked=false;

    public GameShowBuzzerMode(Activity context, RelativeLayout rlayout, int playerNumber) {
        this.myActivity=context;
        this.myLayout=rlayout;
        this.playerNumber=playerNumber;
    }

    public void start() {
        initButtons();
        initButtonListeners();
    }

    private void reset(){
        alreadyClicked=false;
    }

    private void initButtons(){
        buttonBuzzer=new MyButtonBuzzer<GameShowBuzzerPage2>(this.myActivity,myLayout,playerNumber);
    }

    private void initButtonListeners(){
        for(int index=0;index<playerNumber;++index){
            final int playerIndex=index;
            buttonBuzzer.setListener(new Passable() {
                @Override
                public void invoker() {
                    commonButtonHandler(playerIndex);
                }
            }, index);

            buttonBuzzer.activateListener(index);
        }
    }

    private void initDialog(int winner){
        dialog=new GameShowB_Dialog(this.myActivity,
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogRestartHandler();
                    }
                },
                new Passable() {
                    @Override
                    public void invoker() {
                        dialogQuitHandler();
                    }
                },
                winner);
    }

    private void commonButtonHandler(int winner_index) {
        if (alreadyClicked == false) {

            int winner = winner_index + 1;
            initDialog(winner);
            saveResult(winner);
            dialog.showDialog();
            alreadyClicked=true;

        }
    }

    private void saveResult(int winner){
        BuzzerData.saveData((Context)this.myActivity, playerNumber, winner);
    }

    private void dialogRestartHandler(){
        reset();
    }

    private void dialogQuitHandler(){
        destroy();
    }

    private void destroy(){
        this.myActivity.finish();
    }
}
