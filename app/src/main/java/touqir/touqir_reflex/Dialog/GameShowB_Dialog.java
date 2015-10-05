package touqir.touqir_reflex.Dialog;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 28/09/15.
 */
public class GameShowB_Dialog<activityClass> extends MyBasicDialog<activityClass> {

    private String MessagePart1="Player ", MessagePart2=" pressed first.Press Restart when you are ready for next round";
    private String positiveButton="Restart",negativeButton="Quit";

    public GameShowB_Dialog(activityClass activity, Passable positiveBehaviour, Passable negativeBehaviour, int winner) {
        super(activity, positiveBehaviour, negativeBehaviour);
        String message=MessagePart1+Integer.toString(winner)+MessagePart2;
        setMessage(message);
        setPositiveText(positiveButton);
        setNegativeText(negativeButton);
        initDialog();
    }
}
