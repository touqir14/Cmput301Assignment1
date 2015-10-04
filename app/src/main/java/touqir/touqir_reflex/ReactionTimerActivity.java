package touqir.touqir_reflex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Timer;

import touqir.touqir_reflex.Button.MyClickMe;
import touqir.touqir_reflex.Data.ReactionData;
import touqir.touqir_reflex.Dialog.ReactionT_DialogEarly;
import touqir.touqir_reflex.Dialog.ReactionT_DialogIntro;
import touqir.touqir_reflex.Dialog.ReactionT_DialogLater;

public class ReactionTimerActivity extends AppCompatActivity {

    private ReactionTimerMode myReactionTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);
        myReactionTimer=new ReactionTimerMode(this);
        myReactionTimer.startReactionTimerMode();
    }



    public void destroy(){
        finish();
    }
}
