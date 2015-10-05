package touqir.touqir_reflex.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by touqir on 26/09/15.
 */
public class Dialog_YesNo<activityClass> {

    private activityClass parentActivity;
    private String message, positiveText, negativeText;
    private Boolean cancelable=false, createdBuilder=false, createdProperties=false, createdDialog=false;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    public Dialog_YesNo(activityClass activity) {
        this.parentActivity=activity;
        positiveText = "Yes";
        negativeText = "No";
    }

    void createDialogType(){
        this.dialogBuilder = new AlertDialog.Builder((Context) parentActivity);
        createdBuilder=true;
    }

    void setMessage(String Message){
        if (Message.length()>150) {
            throw new RuntimeException("Dialog text is too long");
        }
        this.message=Message;
    }

    void setPositiveText(String text){
        if (text.length()>20) {
            throw new RuntimeException("button text is too long");
        }
        positiveText=text;
    }

    void setNegativeText(String text){
        if (text.length()>20) {
            throw new RuntimeException("button text is too long");
        }
        negativeText=text;
    }

    void setCancelable(Boolean Cancelable) {
        this.cancelable=Cancelable;
    }

    void positiveBehaviour(DialogInterface dialog){

    }

    void negativeBehaviour(DialogInterface dialog){

    }

    public void createDialogBoxProperties(){

        if (createdBuilder==false){
            throw new RuntimeException("Dialog Box Builder(dialogBuilder) object is not created!");
        }
        dialogBuilder
                .setMessage(this.message)
                .setCancelable(this.cancelable)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        positiveBehaviour(dialog);
                    }
                })
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        negativeBehaviour(dialog);
                    }
                });

        createdProperties=true;
    }


    public void createDialog(){

        if (createdProperties==false) {
            throw new RuntimeException("First setup Dialog Box Properties!");
        }
        dialog=dialogBuilder.create();
        createdDialog=true;
    }


    public void showDialog(){

        if (createdDialog==false) {
            throw new RuntimeException("First create Dialog and then call this method to show it");
        }
        dialog.show();
    }
}

