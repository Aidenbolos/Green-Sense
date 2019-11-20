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
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().setTitle("Green Sense");
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_green_one:
                case R.id.nav_green_two:
                    ViewChanger.switchToFragment(BottomNavigationActivity.this, new HomeFragment(), R.id.container);
                    return true;
                case R.id.nav_build:
                    ViewChanger.switchToFragment(BottomNavigationActivity.this, new BuildYourOwnFragment(), R.id.container);
                    return true;
                case R.id.nav_contact:
                    ViewChanger.switchToFragment(BottomNavigationActivity.this, new ContactFragment(), R.id.container);
                    return true;
            }
            return false;
        }
    };



    }



