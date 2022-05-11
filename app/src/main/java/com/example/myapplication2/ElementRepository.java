package com.example.myapplication2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ElementRepository {
    private ElementDao mElementDao;
    private LiveData<List<Phone>> mAllPhones;

    ElementRepository(Application application){
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);
        mElementDao = phoneRoomDatabase.elementDao();
        mAllPhones = mElementDao.getAlphabetizedElements();
        }
        LiveData<List<Phone>> getAllElements(){
            return mAllPhones;
    }
    void deleteAll(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mElementDao.deleteALL();
        });

    }
    void insert(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mElementDao.insert(phone);
        });
    }
    void delete(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mElementDao.delete(phone);
        });
    }
    void update(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mElementDao.update(phone);
        });

    }

}
