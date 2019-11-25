package rad.technologies.greensense;
//R.A.D. Technologies

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
    }
}


