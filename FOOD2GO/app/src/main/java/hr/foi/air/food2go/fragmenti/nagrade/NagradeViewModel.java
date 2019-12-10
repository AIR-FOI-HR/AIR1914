package hr.foi.air.food2go.fragmenti.nagrade;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NagradeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NagradeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}