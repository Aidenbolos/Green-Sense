package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    public static FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);

        final View rootView = findViewById(R.id.LinLayout);
        inputEmail = findViewById(R.id.email);
        inputPassword =  findViewById(R.id.password);
        progressBar =  findViewById(R.id.progressBar);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnGuest = findViewById(R.id.btn_guest);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);

        int BGcolor = sharedPreferences.getInt("BGcolor", 0);
        switch (BGcolor) {
            case 0:
                rootView.setBackgroundColor(Color.parseColor("#dd7e28"));
                break;
            case 1:
                rootView.setBackgroundColor(Color.parseColor("#80CD29"));
                btnLogin.setBackgroundColor(Color.parseColor("#dd7e28"));
                btnGuest.setBackgroundColor(Color.parseColor("#dd7e28"));
                break;
            case 2:
                rootView.setBackgroundColor(Color.parseColor("#A9A9A9"));
                break;
            default:
                rootView.setBackgroundColor(Color.parseColor("#dd7e28"));
                break;
        }

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), R.string.entEmail, Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), R.string.entPass, Toast.LENGTH_SHORT).show();
                return;
            }

            if(!isValidEmail(email)){
                Toast.makeText(getApplicationContext(), R.string.invalEm, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, task -> {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                progressBar.setVisibility(View.GONE);
                if (!task.isSuccessful()) {
                    // there was an error
                    if (password.length() < 6) {
                        inputPassword.setError(getString(R.string.minimum_password));
                    } else {
                        Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    }
                } else {
                    //Commit user email to shared preferences
                    SharedPreferences sharedPref = Login.this.getSharedPreferences("myPrefs", 0);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", email);
                    editor.apply();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        });

        // Capture button clicks
        btnGuest.setOnClickListener(view -> {

            // Start NewActivity.class
            Intent myIntent = new Intent(Login.this, GuestActivity.class);
            startActivity(myIntent);
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}