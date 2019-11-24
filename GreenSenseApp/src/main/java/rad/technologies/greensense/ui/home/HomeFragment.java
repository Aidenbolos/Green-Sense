package rad.technologies.greensense.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import rad.technologies.greensense.R;
import rad.technologies.greensense.PlantInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.fl_plant_cactus)
    FrameLayout cactus;
    @BindView(R.id.fl_plant_herbs)
    FrameLayout herbs;
    @BindView(R.id.fl_plant_ferns)
    FrameLayout ferns;
    @BindView(R.id.fl_plant_confiers)
    FrameLayout confiers;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_temp_humidity, container, false);
        initClick();
        return root;
    }

    private void initClick() {
        ButterKnife.bind(this, root);
        cactus.setOnClickListener(this);
        herbs.setOnClickListener(this);
        ferns.setOnClickListener(this);
        confiers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PlantInfoActivity.class);
        switch (v.getId()) {
            case R.id.fl_plant_cactus:
                intent.putExtra("title", "cactus");
                startActivity(intent);
                break;
            case R.id.fl_plant_ferns:
                intent.putExtra("title", "ferns");
                startActivity(intent);
                break;
            case R.id.fl_plant_herbs:
                intent.putExtra("title", "herbs");
                startActivity(intent);
                break;
            case R.id.fl_plant_confiers:
                intent.putExtra("title", "confiers");
                startActivity(intent);
                break;
        }
    }
}