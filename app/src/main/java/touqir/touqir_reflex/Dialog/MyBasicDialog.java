package touqir.touqir_reflex.Dialog;

import android.content.DialogInterface;

import touqir.touqir_reflex.Passable;

/**
 * Created by touqir on 26/09/15.
 */

public class MyBasicDialog<activityClass> extends Dialog_YesNo<activityClass> {

//    private String earlyMessage="Clicked too early! Want to try again?";
    private Passable positiveBehaviourInvoker, negativeBehaviourInvoker;

    public MyBasicDialog(activityClass activity, Passable positiveBehaviour, Passable negativeBehaviour) {

        super(activity);
        setPositiveBehaviourInvoker(positiveBehaviour);
        setNegativeBehaviourInvoker(negativeBehaviour);
    }

    @Override
    void positiveBehaviour(DialogInterface dialog) {
        positiveBehaviourInvoker.invoker();
        dialog.cancel();
    }

    @Override
    void negativeBehaviour(DialogInterface dialog) {
        negativeBehaviourInvoker.invoker();
        dialog.cancel();
    }

    public void setPositiveBehaviourInvoker(Passable positiveBehaviour){
        positiveBehaviourInvoker=positiveBehaviour;
    }

    public void setNegativeBehaviourInvoker(Passable negativeBehaviour){
        negativeBehaviourInvoker=negativeBehaviour;
    }

    public void initDialog(){
        this.createDialogType();
        this.createDialogBoxProperties();
        this.createDialog();
    }
}
