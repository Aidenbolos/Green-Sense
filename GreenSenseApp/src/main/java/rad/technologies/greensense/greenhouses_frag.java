package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Objects;


public class greenhouses_frag extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_greenhouses_frag, null);

        Spinner selectSpin = root.findViewById(R.id.selected_spin);

        selectSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Fragment fragment = null;
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getSelectedItemPosition()==0){
                    fragment = new greenhouse1_frag();
                    loadFragment(fragment);
                }else if (parent.getSelectedItemPosition()==1) {
                    fragment = new greenhouse2_frag();
                    loadFragment(fragment);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return root;
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.greenhouse_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}