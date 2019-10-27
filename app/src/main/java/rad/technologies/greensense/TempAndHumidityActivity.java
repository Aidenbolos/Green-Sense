package rad.technologies.greensense;
//R.A.D. Technologies
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class TempAndHumidityActivity extends AppCompatActivity implements View.OnClickListener {

    //add Firebase Database stuff
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    SeekBar sbTemp, sbHumidity;
    TextView tvGreenHouseTemp, tvGreenHouseHumidity;
    ImageView ivRefreshTemp, ivRefreshHumidity;

    TextView tvMoistureLevel;
    ImageView ivRefreshMoistureLevel, ivBack;
    SeekBar spMoistureLevel;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_and_humidity);
        sbTemp = findViewById(R.id.sbTemp);
        sbHumidity = findViewById(R.id.sbHumidity);
        ivBack = findViewById(R.id.ivBack);
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
        ivBack.setOnClickListener(this);


        SetRandomTempValue();
        SetRandomHumidityValue();


        sbHumidity.setEnabled(false);
        sbTemp.setEnabled(false);
        spMoistureLevel.setEnabled(false);


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
                                tvGreenHouseHumidity.setText("Humidity = " + document.get("Humidity") + "RM");
                                int hum = ((Long) document.get("Humidity")).intValue();
                                sbHumidity.setProgress(hum);
                            }
                        } else {
                            tvGreenHouseHumidity.setText("Error getting documents." + task.getException());
                        }
                    }
                });
    }

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivBack:
                finish();
                break;
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
