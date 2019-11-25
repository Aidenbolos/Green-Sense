package rad.technologies.greensense;
//R.A.D. Technologies

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class home_frag extends Fragment {

    private ViewFlipper viewFlipper;
    private boolean isSlideshowOn;
    private int FLIP_DURATION = 3500;

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

}
