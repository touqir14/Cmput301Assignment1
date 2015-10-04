package touqir.touqir_reflex.Dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import touqir.touqir_reflex.R;
import touqir.touqir_reflex.Statistics;

public class EmailPage extends AppCompatActivity {

    private String emailTextBody, emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        getEmailTextBody();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_email_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Based on Jeremy Logan's answer at http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
    public void sendEmail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , emailAddress);
        i.putExtra(Intent.EXTRA_SUBJECT, "Statistics from Reflex App");
        i.putExtra(Intent.EXTRA_TEXT   , emailTextBody);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Log.e("EmailPage.java","No email application found",ex);
            throw new RuntimeException();
        }
    }

    public void getEmailTextBody(){
        Intent myIntent=getIntent();
        emailTextBody=myIntent.getStringExtra(Statistics.emailTextKey);
    }

    public void emailButtonHandler(View view) {

        EditText emailField= (EditText) findViewById(R.id.InputEmailID);
        emailAddress=emailField.getText().toString();
        sendEmail();
    }
}

