package com.example.myapplication.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/8/1 13:24
 */
@Database(entities = {FileDownDetail.class},version = 1,exportSchema = false)
public abstract class FileDownDataBase extends RoomDatabase {

    private static FileDownDataBase INSTANCE;

    public static synchronized FileDownDataBase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FileDownDataBase.class,"down_database").build();
        }
        return INSTANCE;
    }

    public abstract FileDownDao getFileDownDao();
}
