package touqir.touqir_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import touqir.touqir_reflex.Button.MyButtonBuzzer;
import touqir.touqir_reflex.Data.BuzzerData;
import touqir.touqir_reflex.Dialog.GameShowB_Dialog;

public class GameShowBuzzer2 extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private int playerNumber;
    private MyButtonBuzzer buttonBuzzer;
    private GameShowB_Dialog dialog;
    private Boolean alreadyClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer2);
        relativeLayout=(RelativeLayout)findViewById(R.id.GameShowBuzzer2);
        getValue();
        initButtons();
        initButtonListeners();
    }

    private void reset(){
        alreadyClicked=false;
    }

    private void getValue(){

        Intent myIntent=getIntent();
        playerNumber=myIntent.getIntExtra(GameShowBuzzer1.numberOfPlayers,2); //2 is default value
    }

    private void initButtons(){
        buttonBuzzer=new MyButtonBuzzer<GameShowBuzzer2>(this,relativeLayout,playerNumber);
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
        dialog=new GameShowB_Dialog<GameShowBuzzer2>(this,
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
        BuzzerData.saveData(this,playerNumber,winner);
    }

    private void dialogRestartHandler(){
        reset();
    }

    private void dialogQuitHandler(){
        destroy();
    }

    private void destroy(){
        finish();
    }
}
