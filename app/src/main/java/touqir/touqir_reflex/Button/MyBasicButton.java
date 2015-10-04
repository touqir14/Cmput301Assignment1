package touqir.touqir_reflex.Button;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 27/09/15.
 */
public class MyBasicButton<contextClass> extends MyButton<contextClass> implements BasicButton {

    private ArrayList<Integer> rules= new ArrayList<Integer>(Arrays.asList(RelativeLayout.CENTER_IN_PARENT));
    private Integer gravity=null;
    private int textSize=20, listenerIndex;
    private String buttonText="Enter";


    public MyBasicButton(LinearLayout layout, contextClass context, int width, int height) {
        super(layout, context, width, height);
    }

    public MyBasicButton(RelativeLayout layout, contextClass context, int width, int height) {
        super(layout, context, width, height);
    }

    public MyBasicButton(Button button) {
        super(button);
    }

    @Override
    public void createMe(){

        setSize(textSize);
        setText(buttonText);
        setLayoutProperties(rules, gravity);
        addToLayout();
        activateListener();
    }

    @Override
    public void setListener(Passable passed){

        listenerIndex=addOnClickListener(passed);
    }

    @Override
    public void activateListener(){
        setOnClickListener(listenerIndex);
    }

    public void setProperties(String buttonText,int buttonSize,int gravity) {

    }

}
