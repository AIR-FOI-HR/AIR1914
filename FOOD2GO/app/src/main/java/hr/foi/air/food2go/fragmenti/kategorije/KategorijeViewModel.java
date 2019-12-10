package hr.foi.air.food2go.fragmenti.kategorije;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KategorijeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KategorijeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is kategorija fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}