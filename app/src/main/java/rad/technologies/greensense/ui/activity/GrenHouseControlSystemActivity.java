package rad.technologies.greensense.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rad.technologies.greensense.R;

import java.util.Objects;

public class GrenHouseControlSystemActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
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
                    Intent intent = new Intent(GrenHouseControlSystemActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    FrameLayout flTempAndHumidity, flManualControl;
    TextView tvTempAndHumidity, tvManualControl;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_device);
        Objects.requireNonNull(getSupportActionBar()).hide();

        flTempAndHumidity = findViewById(R.id.flTempAndHumidity);
        flManualControl = findViewById(R.id.flManualControl);

        tvTempAndHumidity = findViewById(R.id.tvTempAndHumidity);
        tvManualControl = findViewById(R.id.tvManualControl);


        flTempAndHumidity.setOnClickListener(this);
        flManualControl.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        WhiteBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
        WhiteBackgroundFunction(flManualControl, tvManualControl);


        switch (v.getId()) {
            case R.id.flTempAndHumidity:
                GreenBackgroundFunction(flTempAndHumidity, tvTempAndHumidity);
                startActivity(new Intent(GrenHouseControlSystemActivity.this, TempAndHumidityActivity.class));
                break;

            case R.id.flManualControl:
                GreenBackgroundFunction(flManualControl, tvManualControl);
                startActivity(new Intent(GrenHouseControlSystemActivity.this, DevicesActivity.class));

                break;


        }
    }

    private void WhiteBackgroundFunction(FrameLayout frameLayout, TextView textView) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.rounded_button_white));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_white));
        }

        textView.setTextColor(getResources().getColor(R.color.green));

    }

    private void GreenBackgroundFunction(FrameLayout frameLayout, TextView textView) {

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            frameLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.rounded_button_green));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_green));
        }
        textView.setTextColor(getResources().getColor(R.color.white));


    }

}
