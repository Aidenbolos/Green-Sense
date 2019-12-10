package rad.technologies.greensense;
//R.A.D. Technologies

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

public class PlantInfoActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final int pic_id = 123;

    //sign out method
    public void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
    }

    private TextView plantDes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        //Pickup user email from shared preferences
        SharedPreferences sharedPref = getSharedPreferences("myPrefs", 0);
        String defaultEmail = "greensense@gmail.com";
        String email = sharedPref.getString("email", defaultEmail);

        //Manipulate user email to extract organization name
        String org = email.substring(email.lastIndexOf("@") + 1).trim();
        org = org.substring(0, org.lastIndexOf("."));
        org = org.substring(0, 1).toUpperCase() + org.substring(1);

        switch(org) {
            case "Humber":
                myToolbar.setBackgroundColor(getResources().getColor(R.color.humber_blue));
                myToolbar.setTitleTextColor(Color.WHITE);
                break;
            case "York":
                myToolbar.setBackgroundColor(getResources().getColor(R.color.york_red));
                myToolbar.setTitleTextColor(Color.WHITE);
                break;
            default:
                myToolbar.setTitleTextColor(Color.WHITE);
        }

        Intent intent = getIntent();
        plantDes = findViewById(R.id.plantDesc);
        FrameLayout cactus = findViewById(R.id.fl_plant_cactus);
        FrameLayout herbs = findViewById(R.id.fl_plant_herbs);
        FrameLayout ferns = findViewById(R.id.fl_plant_ferns);
        FrameLayout conifers = findViewById(R.id.fl_plant_confiers);

        String name = intent.getStringExtra("Plant");

        assert name != null;
        switch (name) {
            case "cactus":
                plantDes.setText(R.string.cactus);
                break;
            case "ferns":
                plantDes.setText((R.string.ferns));
                break;
            case "herbs":
                plantDes.setText(R.string.herbs);
                break;
            case "conifers":
                plantDes.setText(R.string.conifers);
                break;
        }
        cactus.setOnTouchListener(this);
        herbs.setOnTouchListener(this);
        ferns.setOnTouchListener(this);
        conifers.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.fl_plant_cactus:
                plantDes.setText(R.string.cactus);
                break;
            case R.id.fl_plant_herbs:
                plantDes.setText(R.string.herbs);
                break;
            case R.id.fl_plant_ferns:
                plantDes.setText(R.string.ferns);
                break;
            case R.id.fl_plant_confiers:
                plantDes.setText(R.string.conifers);
                break;
        }
        return false;
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
                    Toast.makeText(this, R.string.featErr,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_about:
                try {
                    startActivity(new Intent(this,AboutUs.class));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_help:
                try {
                    Intent browserHelp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikihow.com/Maintain-a-Greenhouse"));
                    startActivity(browserHelp);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this,  R.string.wrongErr, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                try {
                    startActivity(new Intent(this,SettingsActivity.class));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.featErr,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_signout:
                try {
                    signOut();
                    Intent intent = new Intent(PlantInfoActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

