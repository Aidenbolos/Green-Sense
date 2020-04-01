package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class DevicesActivity extends AppCompatActivity implements View.OnClickListener {

    protected FirebaseAuth auth;
    private FirebaseFirestore db;
    private static final int pic_id = 123;

    //sign out method
    public void signOut() {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
    }
    private TextView tvFanStat, tvPumpStat, tvShadeStat;
    private Switch fanSw, pumpSw, shadeSw;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        db = FirebaseFirestore.getInstance();

        fanSw = findViewById(R.id.fanSw);
        pumpSw = findViewById(R.id.pumpSw);
        shadeSw = findViewById(R.id.shadeSw);

        tvFanStat = findViewById(R.id.tvFan);
        getFanStat();
        tvShadeStat = findViewById(R.id.tvShade);
        getShadeStat();
        tvPumpStat = findViewById(R.id.tvPump);
        getPumpStat();

        final Button button = findViewById(R.id.btnRef);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFanStat();
                getShadeStat();
                getPumpStat();

                if(fanSw.isChecked()){
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "ON");
                    db.collection("Control").document("Fan").set(Active);
                }else{
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "OFF");
                    db.collection("Control").document("Fan").set(Active);
                }

                if(pumpSw.isChecked()){
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "ON");
                    db.collection("Control").document("Pump").set(Active);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getPumpStat();
                        }
                    }, 3000);
                }else{
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "OFF");
                    db.collection("Control").document("Pump").set(Active);
                }

                if(shadeSw.isChecked()){
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "ON");
                    db.collection("Control").document("Shade").set(Active);
                }else{
                    Map<String,Object> Active=new HashMap<>();
                    Active.put("Active", "OFF");
                    db.collection("Control").document("Shade").set(Active);
                }
            }
        });


        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
            case R.id.action_signout:
                try {
                    signOut();
                    Intent intent = new Intent(DevicesActivity.this, Login.class);
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

    private void getFanStat() {
        DocumentReference docRef = db.collection("Control").document("Fan");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        tvFanStat.setText((CharSequence) document.get("Active"));
                        if(document.get("Active").equals("ON")){
                            tvFanStat.setTextColor(getResources().getColor(R.color.green));
                            fanSw.setChecked(true);
                        }else if(document.get("Active").equals("OFF")){
                            tvFanStat.setTextColor(getResources().getColor(R.color.red));
                            fanSw.setChecked(false);
                        }
                    } else {
                        tvFanStat.setText(getString(R.string.docErr) + task.getException());
                    }
                }
            }
        });
    }

    private void getShadeStat() {
        DocumentReference docRef = db.collection("Control").document("Shade");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //tvShadeStat.setText((CharSequence) document.get("Active"));
                        if(document.get("Active").equals("ON")){
                            tvShadeStat.setText("CLOSED");
                            tvShadeStat.setTextColor(getResources().getColor(R.color.green));
                            shadeSw.setChecked(true);
                        }else if(document.get("Active").equals("OFF")){
                            tvShadeStat.setText("OPEN");
                            tvShadeStat.setTextColor(getResources().getColor(R.color.red));
                            shadeSw.setChecked(false);
                        }
                    } else {
                        tvShadeStat.setText(getString(R.string.docErr) + task.getException());
                    }
                }
            }
        });
    }

    private void getPumpStat() {
        DocumentReference docRef = db.collection("Control").document("Pump");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        tvPumpStat.setText((CharSequence) document.get("Active"));
                        if(document.get("Active").equals("ON")){
                            tvPumpStat.setTextColor(getResources().getColor(R.color.green));
                            pumpSw.setChecked(true);
                        }else if(document.get("Active").equals("OFF")){
                            tvPumpStat.setTextColor(getResources().getColor(R.color.red));
                            pumpSw.setChecked(false);
                        }
                    } else {
                        tvPumpStat.setText(getString(R.string.docErr) + task.getException());
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
