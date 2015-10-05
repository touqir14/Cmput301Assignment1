package touqir.touqir_reflex.Dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import touqir.touqir_reflex.EmailManager;
import touqir.touqir_reflex.R;
import touqir.touqir_reflex.Statistics;

public class EmailPage extends AppCompatActivity {

    private String emailTextBody, emailAddress;
    private EmailManager myEmailManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        getEmailTextBody();
        myEmailManager= new EmailManager(this);
    }

    public void getEmailTextBody(){
        Intent myIntent=getIntent();
        emailTextBody=myIntent.getStringExtra(Statistics.emailTextKey);
    }

    public void callEmailButtonHandler(View view) {
        myEmailManager.emailButtonHandler(emailTextBody);
    }
}

