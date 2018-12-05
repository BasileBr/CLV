package fr.essant.basilebyvanu.clv.Model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {



    // Create a LiveData with a String
    private MutableLiveData<Integer> mCurrentPosition;

    public MutableLiveData<Integer> getmCurrentPosition() {
        if (mCurrentPosition == null) {
            mCurrentPosition = new MutableLiveData<Integer>();
        }
        return mCurrentPosition;
    }


}