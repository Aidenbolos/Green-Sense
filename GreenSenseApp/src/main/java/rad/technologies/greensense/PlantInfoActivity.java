package rad.technologies.greensense;
//R.A.D. Technologies

import android.content.ActivityNotFoundException;
import android.content.Intent;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PlantInfoActivity extends AppCompatActivity implements View.OnTouchListener {

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
                    Intent intent = new Intent(PlantInfoActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private TextView plantDes;
    private FrameLayout cactus;
    private FrameLayout herbs;
    private FrameLayout ferns;
    private FrameLayout conifers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        plantDes = findViewById(R.id.plantDesc);
        cactus = (FrameLayout) findViewById(R.id.fl_plant_cactus);
        herbs = (FrameLayout) findViewById(R.id.fl_plant_herbs);
        ferns = (FrameLayout) findViewById(R.id.fl_plant_ferns);
        conifers = (FrameLayout) findViewById(R.id.fl_plant_confiers);

        String name = intent.getStringExtra("Plant");

        if (name.equals("cactus")) {
            plantDes.setText(R.string.cactus);
        } else if (name.equals("ferns")) {
            plantDes.setText((R.string.ferns));
        } else if (name.equals("herbs")) {
            plantDes.setText(R.string.herbs);
        } else if (name.equals("conifers")) {
            plantDes.setText(R.string.conifers);
        }
        cactus.setOnTouchListener(this);
        herbs.setOnTouchListener(this);
        ferns.setOnTouchListener(this);
        conifers.setOnTouchListener(this);
    }

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
                    Toast.makeText(this, R.string.comingSoon, Toast.LENGTH_SHORT).show();
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

