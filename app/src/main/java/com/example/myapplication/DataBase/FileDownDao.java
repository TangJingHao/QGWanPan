package com.example.myapplication.DataBase;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/8/1 13:19
 */
@Dao
public interface FileDownDao {
    @Insert
    void insertDownFile(FileDownDetail... downDetails);

    @Update
    void updateDownFile(FileDownDetail... downDetails);

    @Delete
    void deleteDownFile(FileDownDetail... downDetails);

    @Query("DELETE FROM FileDownDetail")//清空  Query不仅是查询 更高级：可以增删改查
    void deleteAllDownFile();

    @Query("SELECT * FROM FileDownDetail ORDER BY AID DESC")//查询哪张表，选择排序方式，DESC降序
    List<FileDownDetail> getALLDownFile();

}
