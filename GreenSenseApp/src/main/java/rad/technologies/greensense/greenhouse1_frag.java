package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

public class greenhouse1_frag extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_greenhouse_frag, null);

        db = FirebaseFirestore.getInstance();

        flTempAndHumidity = root.findViewById(R.id.flTempAndHumidity);
        flManualControl = root.findViewById(R.id.flManualControl);

        tvTempAndHumidity = root.findViewById(R.id.fl_plant_ferns);
        tvManualControl = root.findViewById(R.id.tvManualControl);

        tvTempStat = root.findViewById(R.id.tempStat);
        getTempStat();
        tvHumStat = root.findViewById(R.id.humStat);
        getHumStat();
        tvWaterStat = root.findViewById(R.id.waterStat);
        getWaterStat();
        tvGasStat = root.findViewById(R.id.gasStat);
        getGasStat();


        flTempAndHumidity.setOnClickListener(this::onClick);
        flManualControl.setOnClickListener(this::onClick);

        return root;

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private FrameLayout flTempAndHumidity, flManualControl;
    private TextView tvTempAndHumidity, tvManualControl, tvTempStat, tvHumStat, tvWaterStat, tvGasStat;
    private FirebaseFirestore db;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private void getTempStat() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            int temp = ((Long) Objects.requireNonNull(document.get("Temp"))).intValue();
                            if(temp >= 26 && temp <= 30){
                                tvTempStat.setText("GREAT");
                                tvTempStat.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                            }else if(temp >= 21 && temp <= 25){
                                tvTempStat.setText("OKAY");
                                tvTempStat.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                            }else if(temp <= 20){
                                tvTempStat.setText("NEEDS ATTENTION (TOO LOW)");
                                tvTempStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }else if (temp > 32){
                                tvTempStat.setText("NEEDS ATTENTION (TOO HIGH)");
                                tvTempStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }
                        }
                    } else {
                        tvTempStat.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }
    private void getHumStat() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            int hum = ((Long) Objects.requireNonNull(document.get("Humidity"))).intValue();
                            if(hum >= 50 && hum <= 70){
                                tvHumStat.setText("GREAT");
                                tvHumStat.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                            }else if(hum >= 71 && hum <= 80){
                                tvHumStat.setText("OKAY");
                                tvHumStat.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                            }else if(hum <= 49){
                                tvHumStat.setText("NEEDS ATTENTION (TOO LOW)");
                                tvHumStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }else if (hum > 81){
                                tvHumStat.setText("NEEDS ATTENTION (TOO HIGH)");
                                tvHumStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }
                        }
                    } else {
                        tvHumStat.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }
    private void getWaterStat() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            int soil = ((Long) Objects.requireNonNull(document.get("Soil"))).intValue();
                            if(soil >= 88 && soil <= 100){
                                tvWaterStat.setText("GREAT");
                                tvWaterStat.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                            }else if(soil >= 70 && soil <= 87){
                                tvWaterStat.setText("OKAY");
                                tvWaterStat.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                            }else if(soil <= 69){
                                tvWaterStat.setText("NEEDS ATTENTION (TOO LOW)");
                                tvWaterStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }else if (soil > 101){
                                tvWaterStat.setText("NEEDS ATTENTION (TOO HIGH)");
                                tvWaterStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }
                        }
                    } else {
                        tvWaterStat.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }
    private void getGasStat() {
        db.collection("Readings").document("Values").collection("Data")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            int airq = ((Long) Objects.requireNonNull(document.get("AirQ"))).intValue();
                            if(airq >= 0 && airq <= 50){
                                tvGasStat.setText("GREAT");
                                tvGasStat.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                            }else if(airq >= 51 && airq <= 100){
                                tvGasStat.setText("OKAY");
                                tvGasStat.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                            }else if(airq <= 101){
                                tvGasStat.setText("NEEDS ATTENTION (UNHEALTHY ENVIRONMENT)");
                                tvGasStat.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            }
                        }
                    } else {
                        tvGasStat.setText(getString(R.string.docErr) + task.getException());
                    }
                });
    }

    private void onClick(View v) {
        WhiteBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
        WhiteBackgroundFunction(flManualControl, tvManualControl);
        switch (v.getId()) {
            case R.id.flTempAndHumidity:
                GreenBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
                startActivity(new Intent(getActivity(), TempAndHumidityActivity.class));
                break;

            case R.id.flManualControl:
                GreenBackgroundFunction(flManualControl, tvManualControl);
                startActivity(new Intent(getActivity(), DevicesActivity.class));

                break;
        }
    }

    private void WhiteBackgroundFunction(FrameLayout frameLayout, TextView textView) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.rounded_button_white));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.rounded_button_white));
        }

        textView.setTextColor(getResources().getColor(R.color.green));
    }

    private void GreenBackgroundFunction(FrameLayout frameLayout, TextView textView) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.rounded_button_green));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.rounded_button_green));
        }
        textView.setTextColor(getResources().getColor(R.color.white));
    }
}