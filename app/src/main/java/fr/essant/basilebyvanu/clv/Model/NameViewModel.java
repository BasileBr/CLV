package fr.essant.basilebyvanu.clv.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NameViewModel extends ViewModel {

    //public MutableLiveData<Integer> mpos;

//
//    //creation d'un LiveData Object pour stocker la position de la vidéo
//    private LiveData<Integer> mCurrentPosition;
//
//    //récupérer la position de la vidéo
//    public LiveData<Integer> getmCurrentPosition() {
//        if (mCurrentPosition == null) {
//            mCurrentPosition = new MutableLiveData<Integer>();
//        }
//        return mCurrentPosition;
//    }
//
//    // modifier la position de la vidéo
//    public void setPosition(int pos){
//        mCurrentPosition.setValue(new Integer(pos));
//    }
//
//// Rest of the ViewModel...
//}
//    public NameViewModel(Application app)
//    {   super(app);
//        mpos = new MutableLiveData<>();
//        this.setPosition(0);
//
//    }
//
//    public LiveData<Integer> getPosition()
//    {
//        return mpos;
//    }
//
//    public void setPosition(int pos)
//    {
//        mpos.setValue(new Integer(pos));
//    }

    // Create a LiveData with a String
    private MutableLiveData<Integer> mCurrentPosition;

//    public NameViewModel (){
//        this.mCurrentPosition = new MutableLiveData<>();
//    }

    public MutableLiveData<Integer> getmCurrentPosition() {
        if (mCurrentPosition == null) {
            mCurrentPosition = new MutableLiveData<Integer>();
        }
        return mCurrentPosition;
    }

//    public LiveData<Integer> getmCurrentPosition(){
//        return mCurrentPosition;
//    }

//    public void setmCurrentPosition(int pos){
//        mCurrentPosition.setValue(new Integer(pos));
//    }


}