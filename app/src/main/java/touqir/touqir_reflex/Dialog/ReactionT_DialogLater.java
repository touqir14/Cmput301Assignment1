package touqir.touqir_reflex.Dialog;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */
public class ReactionT_DialogLater<activityClass> extends MyBasicDialog<activityClass> {

    private String LaterMessage = "You clicked late by ";
    private String positiveText = "Restart", negativeText = "Quit";

    public ReactionT_DialogLater(activityClass activity, Passable positiveBehaviour, Passable negativeBehaviour) {

        super(activity, positiveBehaviour, negativeBehaviour);
        this.setMessage(this.LaterMessage);
        this.setPositiveText(positiveText);
        this.setNegativeText(negativeText);
    }

    public void updateDialogMessage(long latency){

        String NewMessage=LaterMessage + Long.toString(latency) + " milliseconds";
        this.setMessage(NewMessage);
        this.initDialog();
    }
}
