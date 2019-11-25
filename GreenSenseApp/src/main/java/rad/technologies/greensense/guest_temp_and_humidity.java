package rad.technologies.greensense;
//R.A.D. Technologies

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class guest_temp_and_humidity extends Fragment {
    private SeekBar sbTemp, sbHumidity;
    private TextView tvGreenHouseTemp, tvGreenHouseHumidity;

    private TextView tvMoistureLevel;
    private SeekBar spMoistureLevel;

    @BindView(R.id.fl_plant_cactus)
    FrameLayout cactus;
    @BindView(R.id.fl_plant_herbs)
    FrameLayout herbs;
    @BindView(R.id.fl_plant_ferns)
    FrameLayout ferns;
    @BindView(R.id.fl_plant_confiers)
    FrameLayout conifers;
    private View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_guest_temp_and_humidity, container, false);

        sbTemp = root.findViewById(R.id.sbTemp);
        sbHumidity = root.findViewById(R.id.sbHumidity);
        tvGreenHouseHumidity = root.findViewById(R.id.tvGreenHouseHumidity);
        tvGreenHouseTemp = root.findViewById(R.id.tvGreenHouseTemp);
        ImageView ivRefreshHumidity = root.findViewById(R.id.ivRefreshHumidity);
        ImageView ivRefreshTemp = root.findViewById(R.id.ivRefreshTemp);
        tvMoistureLevel = root.findViewById(R.id.tvMoistureLevel);
        ImageView ivRefreshMoistureLevel = root.findViewById(R.id.ivRefreshMoistureLevel);
        spMoistureLevel = root.findViewById(R.id.spMoistureLevel);
        ivRefreshMoistureLevel.setOnClickListener(this::onClick);
        ivRefreshTemp.setOnClickListener(this::onClick);
        ivRefreshHumidity.setOnClickListener(this::onClick);


        SetRandomTempValue();
        SetRandomHumidityValue();
        SetMoistureLevelValue();

        sbHumidity.setEnabled(false);
        sbTemp.setEnabled(false);
        spMoistureLevel.setEnabled(false);
        initClick();

        return root;
    }
    private void initClick() {
        ButterKnife.bind(this, root);
        cactus.setOnClickListener(this::onClick);
        herbs.setOnClickListener(this::onClick);
        ferns.setOnClickListener(this::onClick);
        conifers.setOnClickListener(this::onClick);
    }
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomTempValue() {
        int random = new Random().nextInt((100) + 1);
        tvGreenHouseTemp.setText(String.format("%s%d%s", getString(R.string.tempEq), random, getString(R.string.cels)));
        sbTemp.setProgress(random);
    }


    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetRandomHumidityValue() {
        int random = new Random().nextInt((100) + 1);
        tvGreenHouseHumidity.setText(String.format("%s%d%s", getString(R.string.humEq), random, getString(R.string.humVal)));
        sbHumidity.setProgress(random);
    }

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void SetMoistureLevelValue() {
        int random = new Random().nextInt((100) + 1);
        tvMoistureLevel.setText(String.format("%s%d%s", getString(R.string.soilmoisEq), random, getString(R.string.moisVal)));
        spMoistureLevel.setProgress(random);
    }

    private void onClick(View v) {
        Intent intent = new Intent(getActivity(), PlantInfoActivity.class);
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
            case R.id.fl_plant_cactus:
                intent.putExtra("Plant","cactus");
                startActivity(intent);
                break;
            case R.id.fl_plant_ferns:
                intent.putExtra("Plant", "ferns");
                startActivity(intent);
                break;
            case R.id.fl_plant_herbs:
                intent.putExtra("Plant", "herbs");
                startActivity(intent);
                break;
            case R.id.fl_plant_confiers:
                intent.putExtra("Plant", "conifers");
                startActivity(intent);
                break;
        }
    }
}