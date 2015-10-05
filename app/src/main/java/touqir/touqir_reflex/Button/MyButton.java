package touqir.touqir_reflex.Button;

import android.app.ActionBar;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */

public class MyButton<contextClass> {

    private Button button;
    private LinearLayout layoutLinear=null;
    private RelativeLayout layoutRelative=null;
    private boolean gotLayout=false;
    private int lastListenerIndex=0;
    public static final int matchParent=-1;
    public static final int wrapContent=-2;
    private boolean staticButton;
    private RelativeLayout.LayoutParams relativeLayoutParams;
    private LinearLayout.LayoutParams linearLayoutParams;

    protected ArrayList<View.OnClickListener> listeners= new ArrayList<View.OnClickListener>();

    public MyButton(LinearLayout layout, contextClass context, int height, int width) {
        setLayoutType(layout,height,width);
        initButton(context);
        staticButton=false;
    }

    public MyButton(RelativeLayout layout, contextClass context,int height, int width){
        setLayoutType(layout,height,width);
        initButton(context);
        staticButton=false;
    }

    public MyButton(Button button){
        this.button=button;
        staticButton=true;
    }

    public void initButton(contextClass context){
        button=new Button((Context) context);
    }

    public Button getButton(){
        return this.button;
    }

    public void setLayoutType(LinearLayout layout, int width, int height) {

        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }
        
        layoutLinear=layout;
        gotLayout=true;
        linearLayoutParams=new LinearLayout.LayoutParams(width,height);
    }

    public void setLayoutType(RelativeLayout layout, int width, int height) {

        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }

        layoutRelative = layout;
        gotLayout = true;
        relativeLayoutParams=new RelativeLayout.LayoutParams(width,height);
    }

    public void setLayoutProperties(ArrayList<Integer> rules, Integer gravity){

        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }

        try {
            if ((layoutLinear != null) && (rules==null)) {

                if (gravity!=null) {
                    linearLayoutParams.gravity = gravity;
                }
                this.button.setLayoutParams(linearLayoutParams);
            }

            if ((layoutRelative != null) && (gravity==null)) {

                if (rules!=null) {
                    for (int rule : rules) {
                        addRule(rule);
                    }
                }
                this.button.setLayoutParams(relativeLayoutParams);
            }
        } catch (Exception e){
            Log.e("MyButton","Inside setLayoutProperties",e);
            throw new RuntimeException();
        }
    }

    public void setText(String text){
        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }

        if (text.length()>20){
            throw new RuntimeException("button text is too long");
        }

        this.button.setText(text);
    }

    public void setSize(int size) {
        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }

        this.button.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setTextColor(String color){

        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }
        try {
            int color_code = Color.parseColor(color);
            this.button.setTextColor(color_code);
        } catch (IllegalArgumentException e){
            Log.e("MyButton","Make sure you give a valid color", e);
            throw new RuntimeException();
        }
    }


    public int addOnClickListener(final Passable passed) {

        View.OnClickListener newListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passed.invoker();
            }
        };

        listeners.add(newListener);
        lastListenerIndex=listeners.indexOf(newListener);

        return listeners.indexOf(newListener);
    }

    public View.OnClickListener getOnClickListener(int index){
        return listeners.get(index);
    }

    public void removeOnClickListener(int index){

        View.OnClickListener listenerToDestroy=listeners.remove(index);
        listenerToDestroy=null;
        if (lastListenerIndex==index){
            lastListenerIndex=-1;
        }
    }

    public void setOnClickListener(int index) {
        button.setOnClickListener(listeners.get(index));
    }

    public void setOnClickListener() {

        if (lastListenerIndex!=-1) {
            button.setOnClickListener(listeners.get(lastListenerIndex));
        }

        else {
            throw new RuntimeException("Last listener is not known. Instead use index to access it");
        }
    }

    public void addToLayout(){
        if (staticButton==true){
            throw new RuntimeException("This is a static button whose properties cannot be changed");
        }

        if (layoutLinear != null) {
            layoutLinear.addView(button);
        }

        if (layoutRelative != null) {
            layoutRelative.addView(button);
        }
    }

    public void setID(int ID){
        button.setId(ID);
    }

    public int getID(){
        return button.getId();
    }

    public void addRule(int rule){
        if (relativeLayoutParams!=null){
            relativeLayoutParams.addRule(rule);
        }
    }

    public void addRule(int rule, int ruleParam){
        if (relativeLayoutParams!=null){
            relativeLayoutParams.addRule(rule, ruleParam);
        }
    }
}
