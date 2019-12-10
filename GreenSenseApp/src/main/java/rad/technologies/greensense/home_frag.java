package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.androdocs.httprequest.HttpRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class home_frag extends Fragment {

    private ViewFlipper viewFlipper;
    private boolean isSlideshowOn;

    private static String CITY;
    private int PERMISSION_ID = 44;
    private FusedLocationProviderClient mFusedLocationClient;

    private TextView statusTxt, tempTxt,windTxt, pressureTxt, humidityTxt,errorTxt;
    private ImageView condition;


    private boolean checkPermissions(){
        return ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                Objects.requireNonNull(getActivity()),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Granted. Start getting the location information
    }
    @SuppressLint({"MissingPermission", "WrongConstant"})
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                try {
                                    Geocoder geocoder = new Geocoder(getActivity());
                                    List<Address> addresses;
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    String country = addresses.get(0).getCountryCode();
                                    String cityL = addresses.get(0).getLocality();
                                    home_frag.CITY= cityL +", "+country;
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), R.string.locationOn, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);

        Toolbar myToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.my_toolbar);

        //Pickup user email from shared preferences
        SharedPreferences sharedPref = getActivity().getSharedPreferences("myPrefs", 0);
        String defaultEmail = "greensense@gmail.com";
        String email = sharedPref.getString("email", defaultEmail);

        //Manipulate user email to extract organization name
        String org = email.substring(email.lastIndexOf("@") + 1).trim();
        org = org.substring(0, org.lastIndexOf("."));
        org = org.substring(0, 1).toUpperCase() + org.substring(1);

        //Set title to organization name
        TextView title = view.findViewById(R.id.textView);

        //Set slideshow to organization logo
        ImageView orgImage = view.findViewById(R.id.image_one);

        switch(org) {
            case "Humber":
                title.setText(getString(R.string.welcome_to) + org + getString(R.string.plGreen));
                myToolbar.setBackgroundColor(Color.parseColor("#00008B"));
                myToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
                orgImage.setImageResource(R.drawable.humberlogo);
                break;
            case "York":
                title.setText(getString(R.string.welcome_to) + org + getString(R.string.plGreen));
                myToolbar.setBackgroundColor(Color.parseColor("#DC143C"));
                myToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
                orgImage.setImageResource(R.drawable.yorklogo);
                break;
            default:
                title.setText(getString(R.string.welcome_to) + "GreenSense");
                myToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
                orgImage.setImageResource(R.drawable.plants);
        }

        viewFlipper = view.findViewById(R.id.image_view_flipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));

       startSlideshow();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastLocation();
        new weatherTask().execute();

        TextView tv1 = view.findViewById(R.id.textView3);
        tv1.setText(CITY);
        errorTxt = view.findViewById(R.id.error);
        condition = view.findViewById(R.id.condIcon);
        statusTxt = view.findViewById(R.id.condDescr);
        tempTxt = view.findViewById(R.id.temp);
        windTxt = view.findViewById(R.id.windSpeed);
        pressureTxt = view.findViewById(R.id.press);
        humidityTxt = view.findViewById(R.id.hum);

        return view;
    }

    public void onPause(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        stopSlideshow();

    }

    private void startSlideshow() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.isAutoStart();
            int FLIP_DURATION = 3500;
            viewFlipper.setFlipInterval(FLIP_DURATION);
            viewFlipper.startFlipping();
        }
    }

    private void stopSlideshow() {
        if (viewFlipper.isFlipping()) {
            viewFlipper.stopFlipping();
        }
    }



    @SuppressLint("StaticFieldLeak")
    class weatherTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... args) {
            String API = "c3d526c9c98e4e7df5e0996720673562";
            return HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject sys = jsonObj.getJSONObject("sys");
                JSONObject wind = jsonObj.getJSONObject("wind");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                String temp = main.getString("temp") + "°C";
                String pressure = main.getString("pressure");
                String humidity = main.getString("humidity");
                String windSpeed = wind.getString("speed");
                String weatherDescription = weather.getString("description");

                String address = jsonObj.getString("name") + ", " + sys.getString("country");

                /* Populating extracted data into our views */
                statusTxt.setText(weatherDescription.toUpperCase());

                if (weatherDescription.toLowerCase().contains("clouds")){
                    condition.setImageResource(R.drawable.clouds);
                }
                else if (weatherDescription.toLowerCase().contains("wind")) {
                    condition.setImageResource(R.drawable.wind);
                }
                else if (weatherDescription.toLowerCase().contains("mist")){
                    condition.setImageResource(R.drawable.drop);
                }
                else if (weatherDescription.toLowerCase().contains("rain")){
                    condition.setImageResource(R.drawable.drop);
                }
                else if (weatherDescription.toLowerCase().contains("sunny")){
                    condition.setImageResource(R.drawable.sun);
                }
                else if (weatherDescription.toLowerCase().contains("snow")){
                    condition.setImageResource(R.drawable.snow);
                }
                else if (weatherDescription.toLowerCase().contains("storm")) {
                    condition.setImageResource(R.drawable.storm);
                }
                else {
                    condition.setImageResource(R.drawable.temperature);
                }

                tempTxt.setText(temp);
                windTxt.setText(String.format("%s%s", windSpeed, getString(R.string.windMes)));
                pressureTxt.setText(String.format(" %s%s", pressure, getString(R.string.presMes)));
                humidityTxt.setText(String.format("%s%%", humidity));
            } catch (JSONException e) {
                errorTxt.setText(R.string.dataErr);
                e.printStackTrace();
            }
        }
    }
}

