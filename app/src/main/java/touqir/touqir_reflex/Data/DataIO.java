package touqir.touqir_reflex.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import android.content.Context;
import android.util.Log;

/**
 * Created by touqir on 28/09/15.
 */

// I have used pieces of code from lab3 for saveInFile and loadFromFile
// (https://github.com/touqir14/lonelyTwitter/blob/f15tuesday/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java)
// I have also used http://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime for getting class of generic types.

public class DataIO<DataClass> {

    private Context context;
    private String FILENAME=null;
    private Class dataClassType;
//////////////////////////////////////////////////////////////////////////////////////////////////////

    public DataIO(Context context, Class<DataClass> dataClass){
        this.context=context;
        dataClassType=dataClass;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setFileName(String fileName){
        this.FILENAME=fileName;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<DataClass> loadFromFile(){

        if (FILENAME==null){
            throw new RuntimeException("For loading file from DataIO class, filename has not been provided.");
        }

        DataWrapper<DataClass> myDataWrapper;
        ArrayList<DataClass> dataList;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<DataWrapper<DataClass>>(){}.getType(); //use an overriding function somewhere here. Or you can use generics perhaps...
            myDataWrapper = gson.fromJson(in, listType);
//            dataList.add((DataClass) new Long(3));
            dataList=myDataWrapper.getMyData(dataClassType);
            Integer size=dataList.size();
            Log.e("From DataIO, type of loadedData", size.toString());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //throw new RuntimeException(e);
//            dataList= new ArrayList<DataClass>();
//            dataList.add((DataClass)new Long(4));
//            Log.e("From DataIO, type of loadedData", dataList.get(0).getClass().toString());
//            throw new RuntimeException("gottttt");
            return new ArrayList<DataClass>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

        return dataList;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveInFile(boolean isLoadFromFile,ArrayList<DataClass> dataToSave) {

        ArrayList<DataClass> toWrite;
        if (isLoadFromFile==true) {
            toWrite=loadFromFile();
            toWrite.addAll(dataToSave);
        }
        else {
            toWrite=dataToSave;
        }

        DataWrapper<DataClass> myDataWrapper=new DataWrapper<DataClass>(dataClassType,(ArrayList<Object>)toWrite);
        try {
//            Log.e("From DataIO, type of saveInFile", dataToSave.get(0).getClass().toString());
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(myDataWrapper, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteData(){
        saveInFile(false,new ArrayList<DataClass>());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    public class DataWrapper<DataClass>{
        private ArrayList<Object> myData;

        public DataWrapper(Class dataClassType,ArrayList<Object> toSave) {
            switch (getType(dataClassType)) {
                case 0:
                    myData=toSave;
                    return;
                case 1://Incase of Long
                    myData = wrapLong(toSave);
                    return;
                case 2://Incase of Array<Integer>
                    myData = wrapInteger(toSave);
                    return;
            }
        }

        public ArrayList<Object> wrapLong(ArrayList<Object> data){
            return storeLongToDoubleArrayList(data);
        }

        public ArrayList<Object> wrapInteger(ArrayList<Object> data){
            return storeIntegerToDoubleArrayList(data);
        }

        private ArrayList<Object> storeIntegerToDoubleArrayList(ArrayList<Object> data) {
            ArrayList<Object> myDoubleArray= new ArrayList<Object>();
            for (Object datum: data){
                Integer temporary_data= (Integer) datum;
                myDoubleArray.add(temporary_data.doubleValue());
            }
            return myDoubleArray;
        }

        private ArrayList<Object> loadDoubleToIntegerArrayList(ArrayList<Object> data) {
            ArrayList<Object> myIntegerArray= new ArrayList<Object>();
            for (Object datum: data){
                Double temporary_data=(Double) datum;
                myIntegerArray.add(temporary_data.intValue());
            }
            return myIntegerArray;
        }

        private ArrayList<Object> storeLongToDoubleArrayList(ArrayList<Object> data){
            ArrayList<Object> myDoubleArray= new ArrayList<Object>();
            for (Object datum: data){
                Long temporary_data= (Long) datum;
                myDoubleArray.add(temporary_data.doubleValue());
            }
            return myDoubleArray;
        }

        private ArrayList<Object> loadDoubleToLongArrayList(ArrayList<Object> data){
            ArrayList<Object> myLongArray= new ArrayList<Object>();
            for (Object datum: data){
                Double temporary_data=(Double) datum;
                myLongArray.add(temporary_data.longValue());
            }
            return myLongArray;
        }

        public ArrayList<DataClass> getMyData(Class outputClassType){
            switch (getType(outputClassType)){
                case 0:
                return (ArrayList<DataClass>) myData;

                case 1:
                return (ArrayList<DataClass>)loadDoubleToLongArrayList(myData);

                case 2:
                return (ArrayList<DataClass>)loadDoubleToIntegerArrayList(myData);
            }
            return (ArrayList<DataClass>) myData;
        }

        private int getType(Class dataClassType){
            if (dataClassType==Long.class){
                return 1;
            }
            if (dataClassType==Integer.class){
                return 2;
            }
            return 0;
        }

    }

}
