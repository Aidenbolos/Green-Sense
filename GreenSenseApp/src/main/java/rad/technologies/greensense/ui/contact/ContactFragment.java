package rad.technologies.greensense.ui.contact;
//R.A.D. Technologies
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import rad.technologies.greensense.R;
import rad.technologies.greensense.BottomNavigationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_contact)
    Button btnContact;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_contact, container, false);
        initClick();
        return root;
    }

    private void initClick() {
        ButterKnife.bind(this, root);
        btnContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_contact:
                Toast.makeText(getActivity(), "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
                startActivity(new Intent(getActivity(), BottomNavigationActivity.class));
        }
    }
}