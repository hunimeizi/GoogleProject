package com.xiangxue.googleproject.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao   // Database access object
public interface StudentDao {
    @Insert
    void insertWords(Student... students);

    @Update
    void updateWords(Student... students);

    @Delete
    void deleteWords(Student... students);

    @Query("DELETE FROM student")
    void deleteAllWords();

    @Query("SELECT * FROM student ORDER BY ID DESC")

    //List<Student> getAllWords();
    public LiveData<List<Student>> getAllStudentLive();

}
