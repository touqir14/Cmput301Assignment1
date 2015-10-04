package touqir.touqir_reflex.Data;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by touqir on 28/09/15.
 */
public class ReactionData {

    private final String FILENAME="ReactionData.sav";
    private Class dataClassType=Long.class;
    private ArrayList<Long> latencies=new ArrayList<Long>(), max= new ArrayList<Long>(), min=new ArrayList<Long>();
    private ArrayList<Double> median= new ArrayList<Double>(), average= new ArrayList<Double>();
    private Integer totalElements;
//    private ArrayList<ReactionData> reactionDataList;
    private DataIO<Long> IO_object;

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ReactionData(Context context) {
        IO_object=new DataIO<Long>(context,dataClassType);
        IO_object.setFileName(FILENAME);
        loadLatency();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void loadLatency(){
        latencies=IO_object.loadFromFile();
        totalElements=latencies.size();
//        Long x=latencies.get(1);
//        String name=latencies.get(0).getClass().getName();
//        Log.i("Class of latencies[0] :",name);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveLatency(){
        boolean IsLoadFromFile=false;
        IO_object.saveInFile(IsLoadFromFile,latencies);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteData(){
        IO_object.deleteData();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void saveData(Context context,Long latency){
        boolean IsLoadLatency=false;
        ReactionData toSave= new ReactionData(context);
        toSave.addLatency(latency);
        toSave.saveLatency();
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addLatency(Long latency){
        latencies.add(latency);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void evaluateMax() {
        if (totalElements==0){
            throw new RuntimeException("There is no data to work on");
        }
        int lastIndex = totalElements - 1;
//        Log.i("Class of latencies[0] :",latencies.get(0).getClass().getName());
        max.add(findMax(latencies, 0, lastIndex)); //The first index of max is filled with max of all latencies

        if (lastIndex < 10) {
            max.add(new Long(-1));
        } else {
            max.add(findMax(latencies, lastIndex - 10, lastIndex)); //2nd index of max is filled with max of last 10 latencies
        }

        if (lastIndex < 100) {
            max.add(new Long(-1));
        } else {
            max.add(findMax(latencies,lastIndex-100,lastIndex)); //3rd index of max is filled with max of last 100 latencies
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void evaluateMin(){
        if (totalElements==0){
            throw new RuntimeException("There is no data to work on");
        }
        int lastIndex = totalElements - 1;
        min.add(findMin(latencies, 0, lastIndex)); //The first index of min is filled with min of all latencies

        if (lastIndex < 10) {
            min.add(new Long(-1));
        } else {
            min.add(findMin(latencies, lastIndex - 10, lastIndex)); //2nd index of min is filled with min of last 10 latencies
        }

        if (lastIndex < 100) {
            min.add(new Long(-1));
        } else {
            min.add(findMin(latencies, lastIndex - 100, lastIndex)); //3rd index of min is filled with min of last 100 latencies
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void evaluateMedian(){
        if (totalElements==0){
            throw new RuntimeException("There is no data to work on");
        }
        int lastIndex = totalElements - 1;
        median.add(findMedian(latencies, 0, lastIndex)); //The first index of median is filled with median of all latencies

        if (lastIndex < 10) {
            median.add(new Double(-1));
        } else {
            median.add(findMedian(latencies, lastIndex - 10, lastIndex)); //2nd index of median is filled with median of last 10 latencies
        }

        if (lastIndex < 100) {
            median.add(new Double(-1));
        } else {
            median.add(findMedian(latencies, lastIndex - 100, lastIndex)); //3rd index of median is filled with median of last 100 latencies
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void evaluateAverage(){
        if (totalElements==0){
            throw new RuntimeException("There is no data to work on");
        }
        int lastIndex = totalElements - 1;
        average.add(findAverage(latencies, 0, lastIndex)); //The first index of average is filled with average of all latencies

        if (lastIndex < 10) {
            average.add(new Double(-1));
        } else {
            average.add(findAverage(latencies, lastIndex - 10, lastIndex)); //2nd index of average is filled with average of last 10 latencies
        }

        if (lastIndex < 100) {
            average.add(new Double(-1));
        } else {
            average.add(findAverage(latencies, lastIndex - 100, lastIndex)); //3rd index of average is filled with average of last 100 latencies
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Long> getMax(){
        return max;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Long> getMin(){
        return min;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Double> getMedian(){
        return median;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Double> getAverage(){
        return average;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Object> getPresentableData(){

        ArrayList<Object> presentableData= new ArrayList<Object>();
        if (totalElements==0){
            return presentableData;
        }
        evaluateMax();
        evaluateMedian();
        evaluateMin();
        evaluateAverage();
        presentableData.add(totalElements);

        presentableData.add(max.get(0));
        presentableData.add(min.get(0));
        presentableData.add(average.get(0));
        presentableData.add(median.get(0));

        presentableData.add(max.get(1));
        presentableData.add(min.get(1));
        presentableData.add(average.get(1));
        presentableData.add(median.get(1));

        presentableData.add(max.get(2));
        presentableData.add(min.get(2));
        presentableData.add(average.get(2));
        presentableData.add(median.get(2));

        return presentableData;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////

    public long findMax(ArrayList<Long> arrayList, int rangeLow, int rangeHigh){

        if (rangeHigh<rangeLow){
            throw new RuntimeException("Make sure that rangeHigh >= rangeLow");
        }

        Long max= new Long(0);
        for(int index=rangeLow;index<=rangeHigh;++index){

            Long element=arrayList.get(index);
            if (element>max) {
                max=element;
            }
        }

        return max;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public long findMin(ArrayList<Long> arrayList, int rangeLow, int rangeHigh){

        if (rangeHigh<rangeLow){
            throw new RuntimeException("Make sure that rangeHigh >= rangeLow");
        }

        Long min=new Long(999999999);
        for(int index=rangeLow;index<=rangeHigh;++index){

            Long element=arrayList.get(index);
            if (element<min) {
                min=element;
            }
        }

        return min;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public double findMedian(ArrayList<Long> arrayList, int rangeLow, int rangeHigh) {

        if (rangeHigh<rangeLow){
            throw new RuntimeException("Make sure that rangeHigh >= rangeLow");
        }

        ArrayList<Long> sortedArray= (ArrayList<Long>) arrayList.clone();
        Collections.sort(sortedArray);
        int totalElements=rangeHigh-rangeLow+1;

        if (totalElements<2){
            throw new RuntimeException("For median, atleast 2 elements are needed");
        }

        int middle = totalElements/2;

        if (totalElements % 2 == 1) {
            return sortedArray.get(middle);
        } else {
            return (sortedArray.get(middle-1) + sortedArray.get(middle)) / 2.0;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public double findAverage(ArrayList<Long> arrayList, int rangeLow, int rangeHigh) {

        if (rangeHigh<rangeLow){
            throw new RuntimeException("Make sure that rangeHigh >= rangeLow");
        }

        double totalElements=rangeHigh-rangeLow+1;
        Long sum=new Long(0);
        for(int index=rangeLow;index<=rangeHigh;++index){

            Long element=arrayList.get(index);
            sum=sum+element;
        }

        double avg=sum/totalElements;
        return avg;
    }
}
