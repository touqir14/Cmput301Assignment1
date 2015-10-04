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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void toEmailActivityHandler(View view) {
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
