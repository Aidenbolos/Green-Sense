package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

public class TempAndHumidityActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int pic_id = 123;

    //sign out method
    public void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
    }

    //add Firebase Database stuff
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    SeekBar sbTemp, sbHumidity, sbGasLevel;
    TextView tvGreenHouseTemp, tvGreenHouseHumidity,tvGasLevel;
    ImageView ivRefreshTemp, ivRefreshHumidity, ivRefreshGas;

    TextView tvMoistureLevel;
    ImageView ivRefreshMoistureLevel;
    SeekBar spMoistureLevel;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_and_humidity);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        //Pickup user email from shared preferences
        SharedPreferences sharedPref = getSharedPreferences("myPrefs", 0);
        String defaultEmail = getString(R.string.defaulEmail);
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

        sbTemp = findViewById(R.id.sbTemp);
        sbHumidity = findViewById(R.id.sbHumidity);
        sbGasLevel = findViewById(R.id.sbGasLevel);
        tvGreenHouseHumidity = findViewById(R.id.tvGreenHouseHumidity);
        tvGreenHouseTemp = findViewById(R.id.tvGreenHouseTemp);
        tvGasLevel = findViewById(R.id.tvGasLevel);
        ivRefreshHumidity = findViewById(R.id.ivRefreshHumidity);
        ivRefreshTemp = findViewById(R.id.ivRefreshTemp);
        ivRefreshGas = findViewById(R.id.ivRefreshGasLevel);
        tvMoistureLevel = findViewById(R.id.tvMoistureLevel);
        ivRefreshMoistureLevel = findViewById(R.id.ivRefreshMoistureLevel);
        spMoistureLevel = findViewById(R.id.spMoistureLevel);
        ivRefreshMoistureLevel.setOnClickListener(this);
        ivRefreshTemp.setOnClickListener(this);
        ivRefreshHumidity.setOnClickListener(this);
        ivRefreshGas.setOnClickListener(this);


        SetRandomTempValue();
        SetRandomHumidityValue();
        SetMoistureLevelValue();
        SetGasValue();

        sbHumidity.setEnabled(false);
        sbTemp.setEnabled(false);
        spMoistureLevel.setEnabled(false);
        sbGasLevel.setEnabled(false);


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
            case R.id.action_signout:
                try {
                    signOut();
                    Intent intent = new Intent(TempAndHumidityActivity.this, Login.class);
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

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomTempValue() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            tvGreenHouseTemp.setText(getString(R.string.tempEq) + document.get("Temp") + getString(R.string.cels));
                            int temp = ((Long) Objects.requireNonNull(document.get("Temp"))).intValue();
                            sbTemp.setProgress(temp);
                        }
                    } else {
                        tvGreenHouseTemp.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomHumidityValue() {

        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            tvGreenHouseHumidity.setText(getString(R.string.humEq)+ document.get("Humidity") + getString(R.string.humVal));
                            int hum = ((Long) Objects.requireNonNull(document.get("Humidity"))).intValue();
                            sbHumidity.setProgress(hum);
                        }
                    } else {
                        tvGreenHouseHumidity.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetMoistureLevelValue() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            tvMoistureLevel.setText(getString(R.string.soilmoisEq) + document.get("Soil") + getString(R.string.moisVal));
                            int soil = ((Long) Objects.requireNonNull(document.get("Soil"))).intValue();
                            spMoistureLevel.setProgress(soil);
                        }
                    } else {
                        tvMoistureLevel.setText(getString(R.string.docErr)+ task.getException());
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetGasValue() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            tvGasLevel.setText("Air Quality =" + document.get("AirQ") + "AQI");
                            int airq = ((Long) Objects.requireNonNull(document.get("AirQ"))).intValue();
                            airq = airq / 5;
                            sbGasLevel.setProgress(airq);
                        }
                    } else {
                        tvGasLevel.setText(getString(R.string.docErr) + task.getException());
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
            case R.id.ivRefreshGasLevel:
                SetGasValue();
                break;

        }

    }
}
