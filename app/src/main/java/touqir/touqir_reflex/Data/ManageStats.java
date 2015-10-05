package touqir.touqir_reflex.Data;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import touqir.touqir_reflex.R;

/**
 * Created by touqir on 29/09/15.
 */
public class ManageStats {

    private ListView reactionListView, buzzerListView;
    private ArrayList<String> reactionLatencies= new ArrayList<String>();
    private ArrayList<String> buzzerCounts= new ArrayList<String>();
    private ArrayAdapter<String> adapterReactionLatencies;
    private ArrayAdapter<String> adapterBuzzerCounts;
    private Context context;
    private int textResourceID;
    private ReactionData reactionDataObj;
    private BuzzerData buzzerDataObj;
    private String allTime="for all times= ",
            last10Times="for last 10 times= ",
            last100Times="for last 100 times= ",
            maxTime="Maximum Reaction Times ",
            medTime="Median Reaction Times ",
            avgTime="Average Reaction Times ",
            minTime="Minimum Reaction Times ",
            milli=" milliseconds";
    private String twoPlayerGame="For game of 2 Players: " ,
            threePlayerGame="For game of 3 Players: ",
            fourPlayerGame="For game of 4 Players: ",
            player1Counts="Player 1 counts=",
            player2Counts="Player 2 counts=",
            player3Counts="Player 3 counts=",
            player4Counts="Player 4 counts=";


    public ManageStats(Context context, ListView listViewReaction, ListView listViewBuzzer){
        reactionListView=listViewReaction;
        buzzerListView=listViewBuzzer;
        this.context=context;
//        this.textResourceID=resource;
    }


    public void prepareReactionStats() {
        reactionDataObj = new ReactionData(this.context);
        ArrayList<Object> reactionData = reactionDataObj.getPresentableData();
        if (reactionData.size()==0){
            reactionLatencies.add("Sorry, We dont have any record of your reaction times");
            return;
        }
        Integer totalLatencies = (Integer) reactionData.get(0);

        if (totalLatencies > 0) {
            String averageAll = avgTime + allTime + ((Double) reactionData.get(3)).toString()+milli;
            reactionLatencies.add(averageAll);
        }

        if (totalLatencies > 1) {
            String medianAll = medTime + allTime + ((Double) reactionData.get(4)).toString()+milli;
            reactionLatencies.add(medianAll);
            String maxAll = maxTime + allTime + ((Long) reactionData.get(1)).toString()+milli;
            String minAll = minTime + allTime + ((Long) reactionData.get(2)).toString()+milli;
            reactionLatencies.add(maxAll);
            reactionLatencies.add(minAll);
        }

        if (totalLatencies < 10){
            return;
        }

        String max10 = maxTime + last10Times + ((Long) reactionData.get(5)).toString()+milli;
        String min10 = minTime + last10Times + ((Long) reactionData.get(6)).toString()+milli;
        String avg10 = avgTime + last10Times + ((Double) reactionData.get(7)).toString()+milli;
        String med10 = medTime + last10Times + ((Double) reactionData.get(8)).toString()+milli;
        reactionLatencies.add(max10);
        reactionLatencies.add(min10);
        reactionLatencies.add(avg10);
        reactionLatencies.add(med10);

        if (totalLatencies < 100) {
            return;
        }

        String max100 = maxTime + last100Times + ((Long) reactionData.get(9)).toString()+milli;
        String min100 = minTime + last100Times + ((Long) reactionData.get(10)).toString()+milli;
        String avg100 = avgTime + last100Times + ((Double) reactionData.get(11)).toString()+milli;
        String med100 = medTime + last100Times + ((Double) reactionData.get(12)).toString()+milli;
        reactionLatencies.add(max100);
        reactionLatencies.add(min100);
        reactionLatencies.add(avg100);
        reactionLatencies.add(med100);
    }


    public void prepareBuzzerStats(){
        buzzerDataObj= new BuzzerData(this.context);
        ArrayList<Integer> BuzzerData= buzzerDataObj.getBuzzerCounts();
        if (BuzzerData.size()==0){
            buzzerCounts.add("No Buzzer Round games have been played so far!");
            return;
        }

        String for2Players= twoPlayerGame
                + "\n"
                + player1Counts
                + BuzzerData.get(0).toString()
                + "\n"
                + player2Counts
                + BuzzerData.get(1).toString();
        buzzerCounts.add(for2Players);


        String for3Players= threePlayerGame
                + "\n"
                + player1Counts
                + BuzzerData.get(2).toString()
                + "\n"
                + player2Counts
                + BuzzerData.get(3).toString()
                + "\n"
                + player3Counts
                + BuzzerData.get(4).toString();
        buzzerCounts.add(for3Players);


        String for4Players= fourPlayerGame
                + "\n"
                + player1Counts
                + BuzzerData.get(5).toString()
                + "\n"
                + player2Counts
                + BuzzerData.get(6).toString()
                + "\n"
                + player3Counts
                + BuzzerData.get(7).toString()
                + "\n"
                + player4Counts
                + BuzzerData.get(8).toString();
        buzzerCounts.add(for4Players);
    }


    public void initAdapters(){
        adapterReactionLatencies = new ArrayAdapter<String>(this.context,
                R.layout.list_view, reactionLatencies);
        adapterBuzzerCounts = new ArrayAdapter<String>(this.context,
                R.layout.list_view, buzzerCounts);

        reactionListView.setAdapter(adapterReactionLatencies);
        buzzerListView.setAdapter(adapterBuzzerCounts);

    }


    public void updateAdapters(){
        adapterReactionLatencies.notifyDataSetChanged();
        adapterBuzzerCounts.notifyDataSetChanged();
    }

    public String getReactionTimeInString(){
        String reactionTimeInString= new String();
        for (String s: reactionLatencies){
            reactionTimeInString=reactionTimeInString+s+"\n";
        }
        return reactionTimeInString;
    }


    public String getBuzzerCountsInString(){
        String BuzzerCountsInString= new String();
        for (String s: buzzerCounts){
            BuzzerCountsInString=BuzzerCountsInString+s+"\n";
        }
        return BuzzerCountsInString;
    }

    public String getDataAsEmailTextBody(){
        return "BELOW IS THE REACTION TIME STATISTICS: \n"+"\n"+getReactionTimeInString()
                +"\n"+"BELOW IS THE BUZZER COUNT STATISTICS: \n"+"\n"+getBuzzerCountsInString();
    }

    public void deleteReactionData(){
        reactionDataObj.deleteData();
        reactionLatencies.clear();
        updateAdapters();
    }

    public void deleteBuzzerData(){
        buzzerDataObj.deleteData();
        buzzerCounts.clear();
        updateAdapters();
    }
}
