package rad.technologies.greensense;
//R.A.D. Technologies
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class TempAndHumidityActivity extends AppCompatActivity implements View.OnClickListener {

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
                    Intent intent = new Intent(TempAndHumidityActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    //add Firebase Database stuff
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    SeekBar sbTemp, sbHumidity;
    TextView tvGreenHouseTemp, tvGreenHouseHumidity;
    ImageView ivRefreshTemp, ivRefreshHumidity;

    TextView tvMoistureLevel;
    ImageView ivRefreshMoistureLevel;
    SeekBar spMoistureLevel;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_and_humidity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sbTemp = findViewById(R.id.sbTemp);
        sbHumidity = findViewById(R.id.sbHumidity);
        tvGreenHouseHumidity = findViewById(R.id.tvGreenHouseHumidity);
        tvGreenHouseTemp = findViewById(R.id.tvGreenHouseTemp);
        ivRefreshHumidity = findViewById(R.id.ivRefreshHumidity);
        ivRefreshTemp = findViewById(R.id.ivRefreshTemp);
        tvMoistureLevel = findViewById(R.id.tvMoistureLevel);
        ivRefreshMoistureLevel = findViewById(R.id.ivRefreshMoistureLevel);
        spMoistureLevel = findViewById(R.id.spMoistureLevel);
        ivRefreshMoistureLevel.setOnClickListener(this);
        ivRefreshTemp.setOnClickListener(this);
        ivRefreshHumidity.setOnClickListener(this);


        SetRandomTempValue();
        SetRandomHumidityValue();
        SetMoistureLevelValue();

        sbHumidity.setEnabled(false);
        sbTemp.setEnabled(false);
        spMoistureLevel.setEnabled(false);


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
                    Intent intent = new Intent(TempAndHumidityActivity.this, Login.class);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomTempValue() {

        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                tvGreenHouseTemp.setText("Temperature = " + document.get("Temp") + "C");
                                int temp = ((Long) document.get("Temp")).intValue();
                                sbTemp.setProgress(temp);
                            }
                        } else {
                            tvGreenHouseTemp.setText("Error getting documents." + task.getException());
                        }
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomHumidityValue() {

        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                tvGreenHouseHumidity.setText("Humidity = " + document.get("Humidity") + "RM(%)");
                                int hum = ((Long) document.get("Humidity")).intValue();
                                sbHumidity.setProgress(hum);
                            }
                        } else {
                            tvGreenHouseHumidity.setText("Error getting documents." + task.getException());
                        }
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetMoistureLevelValue() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                tvMoistureLevel.setText("Soil Moisture = " + document.get("Soil") + "rPH");
                                int soil = ((Long) document.get("Soil")).intValue();
                                spMoistureLevel.setProgress(soil);
                            }
                        } else {
                            tvMoistureLevel.setText("Error getting documents." + task.getException());
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivRefreshTemp:
                SetRandomTempValue();
                break;
            case R.id.ivRefreshHumidity:
                SetRandomHumidityValue();
                break;
            case R.id.ivRefreshMoistureLevel:
                SetMoistureLevelValue();
                break;


        }

    }
}
