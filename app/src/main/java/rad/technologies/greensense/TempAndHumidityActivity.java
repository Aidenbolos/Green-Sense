package rad.technologies.greensense;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import rad.technologies.greensense.R;

import java.util.Objects;
import java.util.Random;

public class TempAndHumidityActivity extends AppCompatActivity implements View.OnClickListener {

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

        final int minT = 1;
        final int maxT = 100;
        int randomTemp = new Random().nextInt((maxT - minT) + 1) + minT;
        tvGreenHouseTemp.setText("Temperature   " + randomTemp + "F");
        sbTemp.setProgress(randomTemp);

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomHumidityValue() {


        final int minH = 1;
        final int maxH = 100;
        final int randomHumidity = new Random().nextInt((maxH - minH) + 1) + minH;
        tvGreenHouseHumidity.setText("Humidity=   " + randomHumidity + "RM");
        sbHumidity.setProgress(randomHumidity);
    }

    private void SetMoistureLevelValue() {
        final int min = 1;
        final int max = 100;
        int randomMoistureValue = new Random().nextInt((max - min) + 1) + min;

        tvMoistureLevel.setText("Moisture Level= " + randomMoistureValue + "rPH");
        spMoistureLevel.setProgress(randomMoistureValue);
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
