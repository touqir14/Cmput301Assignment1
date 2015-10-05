package touqir.touqir_reflex.Button;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.Arrays;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 27/09/15.
 */
public class MyButtonBuzzer<contextClass> {

    private ArrayList<Integer> rules=null;
    private Context context;
    private RelativeLayout rLayout;
    private int defaultTextSize=30, playerNumber;
    private Integer gravity=null;
    private ArrayList< MyButton<contextClass> > buttons= new ArrayList< MyButton<contextClass> >();

    public MyButtonBuzzer(Context context, RelativeLayout relativeLayout, int playerNumber){

        this.context= context;
        this.rLayout= relativeLayout;
        this.playerNumber=playerNumber;
        createButtons();
    }

    private void createButtons(){

        int height= 1420/playerNumber; //Height of each of the buttons

        for(int index=0;index<playerNumber;++index){
            buttons.add( buttonCreator(height,index) );
        }
    }

    private MyButton buttonCreator(int height, int index) {
        MyButton<contextClass> button= new MyButton(rLayout,context, MyButton.matchParent, height);
        button.setID(View.generateViewId());
        if (index>0){
            int prevButtonID=buttons.get(buttons.size()-1).getID();
            button.addRule(RelativeLayout.BELOW, prevButtonID);
        }
        else {
            button.addRule(RelativeLayout.ALIGN_PARENT_START);
        }
        button.setLayoutProperties(rules, gravity);
        String buttonText="Player "+Integer.toString(index+1);
        button.setText(buttonText);
        button.setTextColor("black");
        button.setSize(defaultTextSize);
        button.addToLayout();
        return button;
    }

    public void setListener(Passable passed, int index){
        MyButton button=buttons.get(index);
        button.addOnClickListener(passed);
    }

    public void activateListener(int index){
        MyButton button=buttons.get(index);
        button.setOnClickListener();
    }
}
