package hr.foi.air.food2go.fragmenti.stanje_bodova;

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

public class StanjeBodovaFragment extends Fragment {

    private StanjeBodovaViewModel stanjeBodovaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stanjeBodovaViewModel =
                ViewModelProviders.of(this).get(StanjeBodovaViewModel.class);
        View root = inflater.inflate(R.layout.stanje_bodova, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        stanjeBodovaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}