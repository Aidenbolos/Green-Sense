package rad.technologies.greensense;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import rad.technologies.greensense.R;
import rad.technologies.greensense.genrelUtills.ViewChanger;
import rad.technologies.greensense.ui.buildYourOwn.BuildYourOwnFragment;
import rad.technologies.greensense.ui.contact.ContactFragment;
import rad.technologies.greensense.ui.home.HomeFragment;

public class BottomNavigationActivity extends AppCompatActivity {
    final int CAMERA_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        getSupportActionBar().setTitle("Green Sense");
    }
}


