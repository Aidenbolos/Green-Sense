package rad.technologies.greensense;
//R.A.D. Technologies

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class guest_temp_and_humidity extends Fragment {
    SeekBar sbTemp, sbHumidity;
    TextView tvGreenHouseTemp, tvGreenHouseHumidity;
    ImageView ivRefreshTemp, ivRefreshHumidity;

    TextView tvMoistureLevel;
    ImageView ivRefreshMoistureLevel;
    SeekBar spMoistureLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest_temp_and_humidity, container, false);
        sbTemp = view.findViewById(R.id.sbTemp);
        sbHumidity = view.findViewById(R.id.sbHumidity);
        tvGreenHouseHumidity = view.findViewById(R.id.tvGreenHouseHumidity);
        tvGreenHouseTemp = view.findViewById(R.id.tvGreenHouseTemp);
        ivRefreshHumidity = view.findViewById(R.id.ivRefreshHumidity);
        ivRefreshTemp = view.findViewById(R.id.ivRefreshTemp);
        tvMoistureLevel = view.findViewById(R.id.tvMoistureLevel);
        ivRefreshMoistureLevel = view.findViewById(R.id.ivRefreshMoistureLevel);
        spMoistureLevel = view.findViewById(R.id.spMoistureLevel);
        ivRefreshMoistureLevel.setOnClickListener(this::onClick);
        ivRefreshTemp.setOnClickListener(this::onClick);
        ivRefreshHumidity.setOnClickListener(this::onClick);


        SetRandomTempValue();
        SetRandomHumidityValue();
        SetMoistureLevelValue();

        sbHumidity.setEnabled(false);
        sbTemp.setEnabled(false);
        spMoistureLevel.setEnabled(false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomTempValue() {
        int random = new Random().nextInt((100 - 0) + 1) + 0;
        tvGreenHouseTemp.setText("Temperature = "+random+"C");
        sbTemp.setProgress(random);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomHumidityValue() {
        int random = new Random().nextInt((100 - 0) + 1) + 0;
        tvGreenHouseHumidity.setText("Humidity = "+random+"RM(%)");
        sbHumidity.setProgress(random);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetMoistureLevelValue() {
        int random = new Random().nextInt((100 - 0) + 1) + 0;
        tvMoistureLevel.setText("Soil Moisture = "+random+"rPH");
        spMoistureLevel.setProgress(random);
    }

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