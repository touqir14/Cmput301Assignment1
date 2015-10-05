package touqir.touqir_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import touqir.touqir_reflex.Data.ManageStats;
import touqir.touqir_reflex.Dialog.EmailPage;

public class Statistics extends AppCompatActivity {

    private String emailTextBody;
    private ManageStats myStats;

    public final static String emailTextKey="touqir.touqir_reflex.Statistics.email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void onStart()
    {
        super.onStart();
//        initStatsObject();
//        Log.d(tag, "In the onStart() event");
    }

    public void onStop(){
        super.onStop();
        myStats=null;
    }

    public void onResume(){
        super.onResume();
        initStatsObject();
    }

    public void initStatsObject(){
        ListView reactionListView=(ListView)findViewById(R.id.listViewReaction);
        ListView buzzerListView=(ListView)findViewById(R.id.listViewBuzzer);
        myStats=new ManageStats(this,reactionListView,buzzerListView);
        myStats.prepareReactionStats();
        myStats.prepareBuzzerStats();
        myStats.initAdapters();
    }

    public void toEmailActivityHandler(View view) { //Switches to emailActivity page.
        emailTextBody = myStats.getDataAsEmailTextBody();
        Intent toChild= new Intent(this, EmailPage.class);
        toChild.putExtra(emailTextKey,emailTextBody);
        startActivity(toChild);
    }

    public void deleteBuzzerDataHandler(View view) {
        myStats.deleteBuzzerData();
    }

    public void deleteReactionDataHandler(View view) {
        myStats.deleteReactionData();
    }
}
