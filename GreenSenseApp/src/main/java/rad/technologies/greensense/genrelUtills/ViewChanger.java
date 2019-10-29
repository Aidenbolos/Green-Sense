package rad.technologies.greensense.genrelUtills;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ViewChanger {


    public static Fragment switchToFragment(Context context, Fragment fragment, int id) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
        return fragment;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("MySharedPref", 0);
    }

    public static boolean isLogin(Context context){
        return getSharedPreferences(context).getBoolean("isLogin",false);
    }

}