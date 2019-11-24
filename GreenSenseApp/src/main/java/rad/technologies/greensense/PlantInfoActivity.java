package rad.technologies.greensense;
//R.A.D. Technologies
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.view.View.OnClickListener;

import rad.technologies.greensense.R;

public class PlantInfoActivity extends AppCompatActivity {

    private TextView fl_plant_cactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

    }
}
