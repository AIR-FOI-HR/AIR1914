package hr.foi.air.food2go.fragmenti.nagrade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hr.foi.air.food2go.R;

public class NagradeFragment extends Fragment {

    private NagradeViewModel nagradeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nagradeViewModel =
                ViewModelProviders.of(this).get(NagradeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nagrade, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        nagradeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}