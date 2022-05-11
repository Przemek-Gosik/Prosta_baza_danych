package com.example.myapplication2;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final ElementRepository mRepository;
    private final LiveData<List<Phone>> mAllPhones;
    public PhoneViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ElementRepository(application);
        mAllPhones=mRepository.getAllElements();

    }
    LiveData<List<Phone>> getAllPhones(){
       return mAllPhones;
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void insert(Phone phone){
        mRepository.insert(phone);
    }
    public void delete(Phone phone){mRepository.delete(phone);}
    public void update(Phone phone){mRepository.update(phone);}

}
