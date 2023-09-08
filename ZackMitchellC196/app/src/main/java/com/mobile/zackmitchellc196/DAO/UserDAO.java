package com.mobile.zackmitchellc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mobile.zackmitchellc196.Entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM Users ORDER BY UserID ASC")
    List<User> getAllUsers();

    @Query("SELECT * FROM Users WHERE Username = :username")
    User getUsername(String username);
}
