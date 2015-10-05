package touqir.touqir_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.os.Process.killProcess;
import static android.os.Process.myPid;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getReactionTimer(View view) {
        Intent reactionTimer= new Intent(this, ReactionTimerActivity.class);
        //Anything
        startActivity(reactionTimer);
    }

    public void getGameShowBuzzer(View view) {
        Intent gameShowBuzzer1= new Intent(this, GameShowBuzzerPage1.class);
        //Anything
        startActivity(gameShowBuzzer1);
    }

    public void getStatistics(View view) {
        Intent statistics= new Intent(this, Statistics.class);
        //Anything
        startActivity(statistics);
    }

    public void terminate(View view) {
//        finish();
//        System.exit(0);
//        int pid=9
        killProcess(myPid());
//        onDestroy();
    }

//    public void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("fsf", "In the onDestroy() event");
//    }
}
