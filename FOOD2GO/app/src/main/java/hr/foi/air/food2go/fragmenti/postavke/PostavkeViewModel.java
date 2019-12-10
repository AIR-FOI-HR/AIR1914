package hr.foi.air.food2go.fragmenti.postavke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostavkeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostavkeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}