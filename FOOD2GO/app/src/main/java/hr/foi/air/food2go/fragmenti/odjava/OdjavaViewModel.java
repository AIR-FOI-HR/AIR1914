package hr.foi.air.food2go.fragmenti.odjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OdjavaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OdjavaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}