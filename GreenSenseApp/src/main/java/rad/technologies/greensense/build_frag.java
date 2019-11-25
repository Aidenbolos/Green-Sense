package rad.technologies.greensense;
//R.A.D. Technologies

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;


public class build_frag extends Fragment implements View.OnClickListener {
    @BindView(R.id.fl_youtube)
    FrameLayout youtube;
    @BindView(R.id.fl_website)
    FrameLayout website;
    @BindView(R.id.fl_parts)
    FrameLayout parts;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_build_frag, container, false);
        initClick();
        return root;
    }

    private void initClick() {
        ButterKnife.bind(this, root);
        youtube.setOnClickListener(this);
        website.setOnClickListener(this);
        parts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.fl_youtube:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-vPcqhQ8DMg"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
                break;
            case R.id.fl_website:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Greenhouse"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                break;
            case R.id.fl_parts:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.alibaba.com/showroom/greenhouse-parts.html"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                break;

        }
        startActivity(intent);
    }
}