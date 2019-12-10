package hr.foi.air.food2go.fragmenti.stanje_bodova;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StanjeBodovaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StanjeBodovaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}