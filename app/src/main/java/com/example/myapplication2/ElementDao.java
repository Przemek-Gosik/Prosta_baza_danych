package com.example.myapplication2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ElementDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM telefony")
    void deleteALL();



    @Query("SELECT * FROM telefony ORDER BY producent ASC")
    LiveData<List<Phone>> getAlphabetizedElements();

    @Delete()
    void delete(Phone phone);

    @Update(onConflict = OnConflictStrategy.ABORT)
    void update(Phone phone);
}
