package touqir.touqir_reflex.Dialog;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */
public class ReactionT_DialogIntro<activityClass> extends MyBasicDialog<activityClass> {

    private String introMessage = "Wait for the 'click me' text to turn black and instantly click on it to measure your Reflex performance!";
    private String positiveText = "Continue", negativeText = "Go Back";

    public ReactionT_DialogIntro(activityClass activity, Passable positiveBehaviour, Passable negativeBehaviour) {
        super(activity, positiveBehaviour, negativeBehaviour);
        this.setMessage(introMessage);
        this.setPositiveText(positiveText);
        this.setNegativeText(negativeText);
        this.initDialog();
    }
}