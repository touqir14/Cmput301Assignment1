package touqir.touqir_reflex.Button;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */
public class MyClickMe<contextClass> extends MyButton<contextClass> implements ClickMe {

    private int listenerEarlyIndex, listenerLaterIndex;
    private ArrayList<Integer> rules= new ArrayList<Integer>(Arrays.asList(RelativeLayout.CENTER_IN_PARENT));
    private Integer gravity=null;
    private int textSize=60;
    private String buttonText="Click Me";

    public MyClickMe(LinearLayout layout, contextClass context) {
        super(layout, context, matchParent, wrapContent);
    }

    public MyClickMe(RelativeLayout layout, contextClass context) {
        super(layout, context, matchParent, wrapContent);
    }

    @Override
    public void createMe(){
        setSize(textSize);
        setText(buttonText);
        setLayoutProperties(rules, gravity);
        addToLayout();
        activateClickMeEarly();
    }

    @Override
    public void setListenerEarly(Passable passed){
        listenerEarlyIndex=addOnClickListener(passed);
    }

    @Override
    public void setListenerLater(Passable passed){
        listenerLaterIndex=addOnClickListener(passed);
    }

    private void activateListenerEarly(){
        setOnClickListener(listenerEarlyIndex);
    }

    private void activateListenerLater(){
        setOnClickListener(listenerLaterIndex);
    }

    @Override
    public void activateClickMeEarly(){
        setTextColor("white");
        activateListenerEarly();
    }

    @Override
    public void activateClickMeLater(){
        setTextColor("black");
        activateListenerLater();
    }
}
