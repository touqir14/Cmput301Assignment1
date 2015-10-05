package touqir.touqir_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import touqir.touqir_reflex.Button.MyBasicButton;

public class GameShowBuzzerPage1 extends AppCompatActivity {

    private MyNumberPicker numberPicker;
    RelativeLayout relativeLayout;
    MyBasicButton<GameShowBuzzerPage1> enterButton;
    private int playerNumber;
    private Intent gameShowBuzzer2;
    public final static String numberOfPlayers="touqir.touqir_reflex.GameShowBuzzerPage1.playerNumber"; //Act as the
                                // identifier for playerNumber integer while passing onto child

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer1);
        relativeLayout=(RelativeLayout)findViewById(R.id.GameShowBuzzer1);
        initNumberPicker();
        initButton();
    }

    private void initNumberPicker(){
        numberPicker= new MyNumberPicker(this,relativeLayout);
        numberPicker.createNumberPicker();
    }

    private void initButton(){
        Button button=(Button) findViewById(R.id.numberPickerButton);
        enterButton= new MyBasicButton(button);
        enterButton.setListener(new Passable() {
            @Override
            public void invoker() {
                enterButtonHandler();
            }
        });
        enterButton.activateListener();
    }

    private void enterButtonHandler(){
        getPlayerNumber();
        createChildActivity();
        sendValueToChild();
        switchToChild();
    }

    private void getPlayerNumber(){
        playerNumber= numberPicker.getValue();
    }

    private void createChildActivity(){
        gameShowBuzzer2= new Intent(this, GameShowBuzzerPage2.class);
    }

    private void sendValueToChild(){
        gameShowBuzzer2.putExtra(numberOfPlayers, playerNumber);
    }

    private void switchToChild(){
        startActivity(gameShowBuzzer2);
    }
}
