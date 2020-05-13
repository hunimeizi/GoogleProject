package com.xiangxue.googleproject.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// singleton
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase INSTANCE;
    public static synchronized StudentDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class,"student_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract StudentDao getStudnetDao();
}
