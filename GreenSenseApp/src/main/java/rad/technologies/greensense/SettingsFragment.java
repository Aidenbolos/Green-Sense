package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String s) {
        addPreferencesFromResource(R.xml.preferences);
        // implement your settings here
    }
}
