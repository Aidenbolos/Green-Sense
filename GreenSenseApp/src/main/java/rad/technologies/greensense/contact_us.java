package rad.technologies.greensense;
//R.A.D. Technologies

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;


public class contact_us extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_contact)
    Button btnContact;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        initClick();
        return root;
    }

    private void initClick() {
        ButterKnife.bind(this, root);
        btnContact.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            Toast.makeText(getActivity(), R.string.msgSent, Toast.LENGTH_SHORT).show();
            Objects.requireNonNull(getActivity()).finishAffinity();
            startActivity(new Intent(getActivity(), GuestActivity.class));
        }
    }
}