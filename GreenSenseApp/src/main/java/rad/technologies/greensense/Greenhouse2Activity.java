package rad.technologies.greensense;
//R.A.D. Technologies
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Greenhouse2Activity extends AppCompatActivity implements View.OnClickListener {
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
                    Intent intent = new Intent(Greenhouse2Activity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    FrameLayout flTempAndHumidity, flManualControl;
    TextView tvTempAndHumidity, tvManualControl;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenhouse2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flTempAndHumidity = findViewById(R.id.flTempAndHumidity);
        flManualControl = findViewById(R.id.flManualControl);

        tvTempAndHumidity = findViewById(R.id.tvTempAndHumidity);
        tvManualControl = findViewById(R.id.tvManualControl);


        flTempAndHumidity.setOnClickListener(this);
        flManualControl.setOnClickListener(this);


    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {

        WhiteBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
        WhiteBackgroundFunction(flManualControl, tvManualControl);


        switch (v.getId()) {
            case R.id.flTempAndHumidity:
                GreenBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
                startActivity(new Intent(Greenhouse2Activity.this, TempAndHumidityActivity.class));
                break;

            case R.id.flManualControl:
                GreenBackgroundFunction(flManualControl, tvManualControl);
                startActivity(new Intent(Greenhouse2Activity.this, DevicesActivity.class));

                break;


        }
    }
    private void WhiteBackgroundFunction(FrameLayout frameLayout, TextView textView) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.rounded_button_white));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_white));
        }

        textView.setTextColor(getResources().getColor(R.color.green));

    }

    private void GreenBackgroundFunction(FrameLayout frameLayout, TextView textView) {

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.rounded_button_green));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_green));
        }
        textView.setTextColor(getResources().getColor(R.color.white));


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
                    Intent intent = new Intent(Greenhouse2Activity.this, Login.class);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
