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
        // Add exception based checking on the string
        this.message=Message;
    }

    void setPositiveText(String text){
        //Add exception
        positiveText=text;
    }

    void setNegativeText(String text){
        //Add exception
        negativeText=text;
    }

    void setCancelable(Boolean Cancelable) {

        this.cancelable=Cancelable;
    }

    void positiveBehaviour(DialogInterface dialog){

    }

    void negativeBehaviour(DialogInterface dialog){

    }

    void createDialogBoxProperties(){

        if (createdBuilder==false){
            //raise exception
            /////////////
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


    void createDialog(){

        if (createdProperties==false) {
            //raise exception
            //////////
        }

        dialog=dialogBuilder.create();
        createdBuilder=true;

    }


    public void showDialog(){

        if (createdDialog==false) {
            //raise exception
            /////////
        }

        dialog.show();

    }



}

