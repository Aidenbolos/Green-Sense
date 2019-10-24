package rad.technologies.greensense;
//R.A.D. Technologies
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final int pic_id = 123;

    //sign out method
    public void signOut() {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Locate the button in activity_main.xml
        Button button1 = (Button) findViewById(R.id.btn_greenhouse1);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, Greenhouse1Activity.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        Button button2 = (Button) findViewById(R.id.btn_greenhouse2);

        // Capture button clicks
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, Greenhouse2Activity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_picture:
                try {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, pic_id);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Sorry, camera not working :(",  Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_help:
                try {
                    Intent browserHelp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikihow.com/Maintain-a-Greenhouse"));
                    startActivity(browserHelp);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Sorry, can't help :(",  Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                try {
                    Toast.makeText(this, "Settings coming soon",  Toast.LENGTH_SHORT).show();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Sorry, settings not working :(",  Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_signout:
                try {
                    signOut();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Unable to sign out :(",  Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}

