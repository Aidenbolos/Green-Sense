package rad.technologies.greensense;
//R.A.D. Technologies

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
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
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

public class home_frag extends Fragment {

    private ViewFlipper viewFlipper;
    private boolean isSlideshowOn;
    private int FLIP_DURATION = 3500;

    static String CITY;
    String API = "c3d526c9c98e4e7df5e0996720673562";
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    TextView addressTxt, statusTxt, tempTxt,windTxt, pressureTxt, humidityTxt,errorTxt;

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }
    @SuppressLint({"MissingPermission", "WrongConstant"})
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    try {
                                        Geocoder geocoder = new Geocoder(getActivity());
                                        List<Address> addresses = null;
                                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                        String country = addresses.get(0).getCountryCode();
                                        String cityL = addresses.get(0).getLocality();
                                        String City = cityL +", "+country;
                                        home_frag.CITY=City;
                                    }catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), Toast.LENGTH_LONG, R.string.locationOn).show();
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

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);

        Toolbar myToolbar = getActivity().findViewById(R.id.my_toolbar);

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
        title.setText("Welcome to " + org + "'s Greenhouse");

        //Set slideshow to organization logo
        ImageView orgImage = view.findViewById(R.id.image_one);

        switch(org) {
            case "Gmail":
                orgImage.setImageResource(R.drawable.logo);
                break;
            case "Humber":
                myToolbar.setBackgroundColor(Color.parseColor("#171473"));
                orgImage.setImageResource(R.drawable.humberlogo);
                break;
            case "York":
                myToolbar.setBackgroundColor(Color.parseColor("#ff0000"));
                orgImage.setImageResource(R.drawable.yorklogo);
                break;
            default:
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
        addressTxt = view.findViewById(R.id.cityText);
        statusTxt = view.findViewById(R.id.condDescr);
        tempTxt = view.findViewById(R.id.temp);
        windTxt = view.findViewById(R.id.windSpeed);
        pressureTxt = view.findViewById(R.id.press);
        humidityTxt = view.findViewById(R.id.hum);

        return view;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void onPause(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        stopSlideshow();

    }

    private void startSlideshow() {
        if (!viewFlipper.isFlipping()) {
            viewFlipper.isAutoStart();
            viewFlipper.setFlipInterval(FLIP_DURATION);
            viewFlipper.startFlipping();
        }
    }

    private void stopSlideshow() {
        if (viewFlipper.isFlipping()) {
            viewFlipper.stopFlipping();
        }
    }

    class weatherTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API);
            return response;
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
                addressTxt.setText(address);
                statusTxt.setText(weatherDescription.toUpperCase());
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

