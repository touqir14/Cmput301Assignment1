package touqir.touqir_reflex;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by touqir on 04/10/15.
 */
public class EmailManager {
    private String[] emailAddress;
    private Activity myActivity;
    public EmailManager(Activity activity){
        myActivity=activity;
    }

    //Based on Jeremy Logan's answer at http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
    public void sendEmail(String emailTextBody){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , emailAddress);
        i.putExtra(Intent.EXTRA_SUBJECT, "Statistics from Reflex App");
        i.putExtra(Intent.EXTRA_TEXT   , emailTextBody);
        try {
            myActivity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Log.e("EmailPage.java", "No email application found", ex);
            throw new RuntimeException();
        }
    }

    public void emailButtonHandler(String emailTextBody) {

        EditText emailField= (EditText) myActivity.findViewById(R.id.InputEmailID);
        emailAddress=emailField.getText().toString().split(" ");
        //Log.e("email_________",emailAddress);
        sendEmail(emailTextBody);
    }

}
