package rad.technologies.greensense.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import rad.technologies.greensense.R;

import java.util.Objects;

public class DevicesActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView ivBack;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ivBack=findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
