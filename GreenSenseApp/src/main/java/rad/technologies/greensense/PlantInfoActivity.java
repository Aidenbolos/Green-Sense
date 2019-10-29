package rad.technologies.greensense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import rad.technologies.greensense.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantInfoActivity extends AppCompatActivity {

    @BindView(R.id.tv_plan_title)
    TextView tvPlantTitle;
    @BindView(R.id.plant_description)
    TextView tvPlantDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvPlantTitle.setText(getIntent().getExtras().getString("title"));
        if (getIntent().getExtras().getString("title").equals("cactus")){
            tvPlantDescription.setText(R.string.cactus);
        }else if (getIntent().getExtras().getString("title").equals("herbs"))
        {
            tvPlantDescription.setText(R.string.herbs);

        }else if (getIntent().getExtras().getString("title").equals("ferns")){
            tvPlantDescription.setText(R.string.ferns);

        }else if (getIntent().getExtras().getString("title").equals("confiers")){
            tvPlantDescription.setText(R.string.confiers);
        }


    }
}
