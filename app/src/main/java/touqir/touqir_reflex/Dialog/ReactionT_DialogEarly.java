package touqir.touqir_reflex.Dialog;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */

public class ReactionT_DialogEarly<activityClass> extends ReactionT_Dialog<activityClass> {

    private String earlyMessage="Clicked too early! Want to try again?";

    public ReactionT_DialogEarly(activityClass activity, Passable positiveBehaviour, Passable negativeBehaviour) {

        super(activity, positiveBehaviour, negativeBehaviour);
        this.setMessage(this.earlyMessage);
        this.initDialog();
    }

}
