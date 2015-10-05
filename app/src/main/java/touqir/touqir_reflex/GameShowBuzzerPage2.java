package touqir.touqir_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class GameShowBuzzerPage2 extends AppCompatActivity {


    private GameShowBuzzerMode myGameShowBuzzer;
    private RelativeLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer2);
        myLayout = (RelativeLayout) findViewById(R.id.GameShowBuzzer2);
        myGameShowBuzzer = new GameShowBuzzerMode(this, myLayout, getPlayerNumber());
        myGameShowBuzzer.start();
    }

    private int getPlayerNumber() {
        Intent myIntent = getIntent();
        return myIntent.getIntExtra(GameShowBuzzerPage1.numberOfPlayers, 2); //2 is default value
    }

    protected void onStart(){
        super.onStart();
    }

    public void destroy(){
        this.finish();
    }
}

