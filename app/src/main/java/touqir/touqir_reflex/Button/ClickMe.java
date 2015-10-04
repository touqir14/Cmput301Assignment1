package touqir.touqir_reflex.Button;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 27/09/15.
 */

public interface ClickMe extends InitButton{

    public void setListenerEarly(Passable passed);
    public void setListenerLater(Passable passed);
    public void activateClickMeEarly();
    public void activateClickMeLater();

}
