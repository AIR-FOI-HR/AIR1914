package hr.foi.air.food2go.fragmenti.postavke;

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

public class PostavkeFragment extends Fragment {

    private PostavkeViewModel postavkeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postavkeViewModel =
                ViewModelProviders.of(this).get(PostavkeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_postavke, container, false);
        final TextView textView = root.findViewById(R.id.header_postavke);
        postavkeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}