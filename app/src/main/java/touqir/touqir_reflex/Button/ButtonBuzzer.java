package touqir.touqir_reflex.Button;

import android.widget.RelativeLayout;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 27/09/15.
 */
public interface ButtonBuzzer<contextClass> {
    public void setListener(Passable passed, int index);
    public void activateListener(int index);
}
