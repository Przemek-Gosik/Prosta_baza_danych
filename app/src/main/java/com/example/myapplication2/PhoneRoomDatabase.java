package com.example.myapplication2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class},version = 1,exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract ElementDao elementDao();

    private static volatile PhoneRoomDatabase INSTANCE;
    static PhoneRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PhoneRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PhoneRoomDatabase.class,"telefony")
                    .addCallback(sRoomDatabaseCallback)
                    .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                ElementDao elementDao = INSTANCE.elementDao();
                Phone phone1 = new Phone("Samsung","Galaxy12","11","www.costam.pl");
                Phone phone2 = new Phone("Xiaomi","12Pro","9","www.page.com");
                elementDao.insert(phone1);
                elementDao.insert(phone2);
            });
        }
    };
}
