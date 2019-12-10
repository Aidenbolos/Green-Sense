package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

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
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private static final int pic_id = 123;

    RadioButton orange, green, grey;

    //sign out method
    public void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.setTitle));
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

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        orange = findViewById(R.id.col3);
        green = findViewById(R.id.col1);
        grey = findViewById(R.id.col2);

        final RadioGroup group = findViewById(R.id.colorRadios);

        int BG_set = sharedPreferences.getInt("BGcolor", 0);
        switch (BG_set){
            case 0:
                group.check(R.id.col1);
                break;
            case 1:
                group.check(R.id.col2);
                break;
            case 2:
                group.check(R.id.col3);
                break;
            default:
                group.check(R.id.col1);
                break;
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = group.getCheckedRadioButtonId();
                RadioButton rb = group.findViewById(id);
                switch (id) {
                    case R.id.col1:
                        // Your code
                        editor.putInt("BGcolor", 0);
                        break;
                    case R.id.col2:
                        // Your code
                        editor.putInt("BGcolor", 1);
                        break;
                    case R.id.col3:
                        // Your code
                        editor.putInt("BGcolor", 2);
                        break;
                    default:
                        // Your code
                        break;
                }
            }
        });


        Button btn = findViewById(R.id.settings_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                editor.apply();
                Toast.makeText(SettingsActivity.this, R.string.setSave, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_picture:
                try {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, pic_id);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.featErr, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.action_help:
                try {
                    Intent browserHelp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikihow.com/Maintain-a-Greenhouse"));
                    startActivity(browserHelp);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, R.string.sett_redirect, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_signout:
                try {
                    signOut();
                    Intent intent = new Intent(SettingsActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
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
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}

