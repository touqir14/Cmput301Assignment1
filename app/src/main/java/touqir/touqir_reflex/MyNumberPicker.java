package touqir.touqir_reflex;

import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.content.Context;

/**
 * Created by touqir on 27/09/15.
 */
class MyNumberPicker<contextClass> {

    private NumberPicker numberPicker;
    private RelativeLayout relativeLayout;
    private int minValue=2,maxValue=4;

    public MyNumberPicker(contextClass context,RelativeLayout rLayout){
        numberPicker=new NumberPicker((Context) context);
        relativeLayout=rLayout;
    }

    private void addToLayout(){
        relativeLayout.addView(numberPicker);
    }


    public void createNumberPicker( ) {

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        numberPicker.setLayoutParams(params);
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setWrapSelectorWheel(true);

        addToLayout();
    }

    public int getValue(){
        return numberPicker.getValue();
    }

}
