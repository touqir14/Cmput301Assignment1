package touqir.touqir_reflex.Data;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import touqir.touqir_reflex.Button.InitButton;

/**
 * Created by touqir on 28/09/15.
 */
public class BuzzerData {

    private final String FILENAME="BuzzerData.sav";
    private Class dataClassType=Integer.class;
    private ArrayList<Integer> buzzerCounts;
    private DataIO<Integer> IO_object;

    public BuzzerData(Context context) {
        IO_object=new DataIO<Integer>(context,dataClassType);
        IO_object.setFileName(FILENAME);
        loadData();
        if (buzzerCounts.size()==0){
            initCountsArray();
        }
    }


    public void loadData() {
        buzzerCounts=IO_object.loadFromFile();
    }

    public void initCountsArray(){
        Integer[] playerinitCount={0,0,0,0,0,0,0,0,0};
        buzzerCounts=new ArrayList<Integer>(Arrays.asList(playerinitCount));
    }

    public void updateCounts(int playerNumber, int winner){
        int offset=winner-1;
        int winnerCounts;
        switch(playerNumber){
            case(2):
                int segmentIndex2=0;
                winnerCounts=buzzerCounts.get(segmentIndex2 + offset);
                winnerCounts=winnerCounts+1;
                buzzerCounts.set(segmentIndex2 + offset, winnerCounts);
                break;
            case(3):
                int segmentIndex3=2;
                winnerCounts=buzzerCounts.get(segmentIndex3 + offset);
                winnerCounts=winnerCounts+1;
                buzzerCounts.set(segmentIndex3 + offset, winnerCounts);
                break;
            case(4):
                int segmentIndex4=5;
                winnerCounts=buzzerCounts.get(segmentIndex4 + offset);
                winnerCounts=winnerCounts+1;
                buzzerCounts.set(segmentIndex4 + offset, winnerCounts);
                break;
        }
    }

    public void saveCounts(int playerNumber, int winner){
        updateCounts(playerNumber, winner);
        boolean IsLoadFromFile=false;
        IO_object.saveInFile(IsLoadFromFile,buzzerCounts);
    }

    public static void saveData(Context context,int playerNumber, int winner){
        BuzzerData toSave= new BuzzerData(context);
        toSave.saveCounts(playerNumber, winner);
    }

    public ArrayList<Integer> getBuzzerCounts() {
        return buzzerCounts;
    }

    public void deleteData(){
        IO_object.deleteData();
    }

}
